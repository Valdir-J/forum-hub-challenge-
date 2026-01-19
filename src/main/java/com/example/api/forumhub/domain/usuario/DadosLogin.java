package com.example.api.forumhub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosLogin(
        @NotBlank
        @Email
        String login,
        @NotBlank
        String senha) {
}
