package com.frota.project.dtos.ExitRecord;

import java.util.UUID;

public record InputCreateExitRecordDto(
        UUID fk_car_frota,
        UUID fk_car_request,
        String observation,
        int km_exit
) {}
