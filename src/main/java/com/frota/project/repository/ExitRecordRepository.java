package com.frota.project.repository;

import com.frota.project.dtos.ExitRecord.OutputUUIDAndLocalDateTimeExitsRecord;
import com.frota.project.dtos.ExitRecord.UUIDAndLocalDateTimeExitsProjection;
import com.frota.project.dtos.cars.frotas.OutputUUIDAndNumCarRecordDto;
import com.frota.project.model.ExitModel;
import com.frota.project.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ExitRecordRepository extends JpaRepository<ExitModel, UUID> {

    @Query("SELECT e FROM ExitModel e WHERE e.fk_user = :userFk ORDER BY e.date_exit DESC")
    Page<ExitModel> findAllOrdered(@Param("userFk") UserModel userFk, Pageable pageable);


    @Query(value = "SELECT e.id_exit_record, e.date_exit FROM ExitModel e " +
            "LEFT JOIN ArrivalModel a ON a.fk_exit_record.id_exit_record = e.id_exit_record " +
            "WHERE a.id_arrival_record IS NULL " +
            "AND e.fk_user.id_user = :userFk ORDER BY e.date_exit DESC")
    Page<OutputUUIDAndLocalDateTimeExitsRecord> findIsNullIdArrival(@Param("userFk") UUID userFk, Pageable pageable);


    @Query(value = "SELECT e.fk_car_frota.id_frota, c.num_car FROM ExitModel e " +
            "LEFT JOIN CarFrotaModel c ON c.id_frota = e.fk_car_frota.id_frota " +
            "WHERE c.id_frota IS NOT NULL " +
            "AND e.fk_user.id_user = :userFk  " +
            "GROUP BY e.fk_car_frota.id_frota, c.num_car " +
            "ORDER BY MAX(e.date_exit) DESC")
    Page<OutputUUIDAndNumCarRecordDto> findLastUsedCars(@Param("userFk") UUID userFk, Pageable pageable);


}
