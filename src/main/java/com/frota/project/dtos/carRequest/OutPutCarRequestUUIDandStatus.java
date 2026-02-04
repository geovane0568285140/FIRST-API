package com.frota.project.dtos.carRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record OutPutCarRequestUUIDandStatus(
        UUID uuid,
        int n_mov,
        LocalDateTime requested_at,
        String status
) {
}
