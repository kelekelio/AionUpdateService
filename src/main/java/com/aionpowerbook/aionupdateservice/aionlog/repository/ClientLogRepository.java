package com.aionpowerbook.aionupdateservice.aionlog.repository;

import com.aionpowerbook.aionupdateservice.aionlog.entity.ClientLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientLogRepository extends JpaRepository<ClientLog, Long> {

    @Query("select l " +
            "from ClientLog l " +
            "where l.aionClientId = :id " +
            "order by l.timestamp desc ")
    List<ClientLog> findAllForClientId(Long id);
}
