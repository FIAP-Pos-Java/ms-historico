package br.com.medtech.ms_historico.dtos;

import java.util.UUID;

public record UsuarioDTO(
    UUID id,
    String nome,
    String email,
    String role
) {}
