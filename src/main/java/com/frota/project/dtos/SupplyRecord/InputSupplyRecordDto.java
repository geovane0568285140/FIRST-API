package com.frota.project.dtos.SupplyRecord;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record InputSupplyRecordDto(
        UUID fk_car_frota,
        UUID fk_user,
        BigDecimal value_total,
        BigDecimal litros,
        int km_record,
        int n_mov
) {
}
