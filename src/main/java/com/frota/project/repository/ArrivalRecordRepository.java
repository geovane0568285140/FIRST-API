package com.frota.project.repository;

import com.frota.project.model.ArrivalModel;
import com.frota.project.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ArrivalRecordRepository extends JpaRepository<ArrivalModel, UUID> {

    @Query("SELECT a FROM ArrivalModel a WHERE a.fk_user = :userFk ORDER BY a.created_at DESC")
    Page<ArrivalModel> findAllOrdered(@Param("userFk") Optional<UserModel> userFk, Pageable pageable);
}
