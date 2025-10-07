package br.com.medtech.ms_historico.dtos;

import br.com.medtech.ms_historico.entities.StatusConsulta;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaDTO(
    UUID id,
    UUID pacienteId,
    UUID medicoId,
    UUID enfermeiroId,
    LocalDateTime dataHora,
    String observacoes,
    StatusConsulta status,
    LocalDateTime criadaEm,
    LocalDateTime atualizadaEm
) {}
