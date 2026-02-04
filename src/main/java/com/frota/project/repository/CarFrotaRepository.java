package com.frota.project.repository;


import com.frota.project.dtos.cars.frotas.OutputUUIDAndNumCarRecordDto;
import com.frota.project.model.CarFrotaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CarFrotaRepository extends JpaRepository<CarFrotaModel, UUID> {


    @Query("SELECT " +
            "e.id_frota, e.num_car  " +
            "FROM CarFrotaModel e ORDER BY e.created_at")
    Page<OutputUUIDAndNumCarRecordDto> findAllOrdered(Pageable pageable);

    Optional<CarFrotaModel> findByLicensePlate(String licensePlate);
}
