package com.frota.project.dtos.ExitRecord;

import java.time.LocalDateTime;
import java.util.UUID;

public record OutputExitRecord(
        UUID id_exit_record,
        UUID fk_car_frota,
        UUID fk_user,
        UUID fk_car_request,
        UUID fk_observation,
        LocalDateTime date_exit,
        int km_exit,
        int n_mov
) {
}
