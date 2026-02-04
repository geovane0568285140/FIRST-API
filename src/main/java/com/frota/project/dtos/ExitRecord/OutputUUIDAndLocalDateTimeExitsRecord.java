package com.frota.project.dtos.ExitRecord;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public record OutputUUIDAndLocalDateTimeExitsRecord(
        UUID id_exit_record,
        LocalDateTime date_exit
) {
}
