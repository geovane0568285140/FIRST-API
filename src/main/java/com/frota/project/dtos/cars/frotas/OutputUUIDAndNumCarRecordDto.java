package com.frota.project.dtos.cars.frotas;

import java.util.UUID;

public record OutputUUIDAndNumCarRecordDto(
        UUID id_frota,
        int num_car
){}
