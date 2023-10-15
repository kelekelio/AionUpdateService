package com.aionpowerbook.aionupdateservice.update.repository;

import com.aionpowerbook.aionupdateservice.update.entity.ClientUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientUpdateRepository extends JpaRepository<ClientUpdate, Long> {

    @Query("select u " +
            "from ClientUpdate u " +
            "where u.aionClientId = :id " +
            "order by u.timestamp desc ")
    List<ClientUpdate> findAllForClientId(Long id);
}
