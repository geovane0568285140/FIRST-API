package com.frota.project.dtos.users;

import com.frota.project.model.UserRole;

import java.time.LocalDateTime;

public record RegisterRecordDto(
        String full_name,
        String name_user,
        String email,
        String password,
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
