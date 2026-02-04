package com.frota.project.dtos.users;

public record AuthenticationDto(
        String email,
        String password
) {
}