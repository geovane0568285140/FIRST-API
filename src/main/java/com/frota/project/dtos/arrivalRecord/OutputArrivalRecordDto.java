package com.frota.project.dtos.arrivalRecord;

import java.time.LocalDateTime;
import java.util.UUID;

public record OutputArrivalRecordDto(
        UUID id_arrival_record,
        UUID fk_exit_record,
        UUID fk_observation,
        UUID fk_user,
        LocalDateTime date_arrival,
        int km_arrival,
        int n_mov
) {
}
