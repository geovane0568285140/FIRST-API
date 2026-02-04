package com.frota.project.dtos.carRequest;

import com.frota.project.model.UserModel;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public record OutPutCarRequestRecordDto(
        UUID uuid,
        UUID fk_user,
        String origin,
        String destination,
        String reason,
        LocalDateTime requested_at,
        String status
) {
}
