package com.frota.project.dtos.carRequest;

import com.frota.project.model.UserModel;

import java.sql.Timestamp;
import java.util.UUID;

public record InputCarRequestRecordDto(
        String origin,
        Integer n_mov,
        String destination,
        String reason,
        String status,
        Boolean active
) {
}
