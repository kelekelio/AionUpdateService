package com.aionpowerbook.aionupdateservice.update.repository;

import com.aionpowerbook.aionupdateservice.update.entity.ClientFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientFileRepository extends JpaRepository<ClientFile, Long> {
}
