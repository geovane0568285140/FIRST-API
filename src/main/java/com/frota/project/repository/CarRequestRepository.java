package com.frota.project.repository;

import com.frota.project.dtos.carRequest.OutPutCarRequestUUIDandStatus;
import com.frota.project.model.CarRequestModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CarRequestRepository extends JpaRepository<CarRequestModel, UUID> {


    @Query("SELECT c.id_car_request, c.n_mov, c.requested_at, c.status " +
            "FROM CarRequestModel c WHERE c.fk_user.id_user = :userFk " +
            "ORDER BY c.requested_at DESC")
    Page<OutPutCarRequestUUIDandStatus> findLastRequests(@Param("userFk") UUID userFk, Pageable pageable);


    @Query("SELECT c.id_car_request, c.n_mov, c.requested_at, c.status " +
            "FROM CarRequestModel c " +
            "WHERE c.fk_user.id_user = :userFk " +
            "AND c.status = :statusParam " +
            "ORDER BY c.requested_at DESC")
    Page<OutPutCarRequestUUIDandStatus> findLastRequestsStatusInPending(@Param("userFk") UUID userFk, @Param("statusParam") String statusParam, Pageable pageable);

}
