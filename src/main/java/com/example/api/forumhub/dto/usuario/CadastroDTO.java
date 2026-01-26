package com.example.api.forumhub.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CadastroDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha
) {
}