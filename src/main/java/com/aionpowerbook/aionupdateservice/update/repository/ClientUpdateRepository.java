package com.aionpowerbook.aionupdateservice.update.repository;

import com.aionpowerbook.aionupdateservice.update.entity.ClientUpdate;
import com.aionpowerbook.aionupdateservice.update.enums.UpdateStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientUpdateRepository extends JpaRepository<ClientUpdate, Long> {

    @EntityGraph(attributePaths = {"files"})
    @Query("select u " +
            "from ClientUpdate u " +
            "where u.aionClientId = :id " +
            "order by u.createdDateTime desc ")
    List<ClientUpdate> findAllForClientId(Long id);

    @Transactional
    @Modifying
    @Query("update ClientUpdate u " +
            "set u.updateStatus = :updateStatus " +
            "where u.id = :id ")
    void updateStatus(Long id, UpdateStatus updateStatus);
}
