package com.frota.project.dtos.cars.frotas;


import java.time.LocalDate;


public record InputCarFrotaRecordDto(
        String license_plate,
        String model,
        Boolean active,
        String mark,
        String manufaturing_year,
        String model_year,
        String color,
        String category,
        String fuel_type,
        String current_mileage,
        String num_crlv,
        LocalDate date_licensing,
        LocalDate date_maturity_IPVA,
        int num_car
) {}

