package com.frota.project.dtos.users;

import com.frota.project.model.UserRole;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public record OutPutUserRecordDto(
        String fullName,
        String name_user,
        String password_hash,
        String email,
        UserRole type_user,
        Boolean active,
        String cpf,
        LocalDateTime date_brith,
        String num_cnh,
        String category_cnh,
        LocalDateTime date_emission_cnh,
        LocalDateTime date_validity_cnh,
        String registration_renach_cnh
) {
}
