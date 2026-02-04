package com.frota.project.dtos.ExitRecord;

import java.sql.Timestamp;

public record UUIDAndLocalDateTimeExitsProjection(
        String id_exit_record,
        Timestamp date_exit
) {
}
