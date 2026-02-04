package com.frota.project.dtos.ExitRecord;

import java.util.UUID;

public record InputUpdateExitRecordDto(
        UUID fk_car_frota,
        UUID fk_car_request,
        UUID fk_observation,
        int km_exit
) {
}
