package br.com.medtech.ms_historico.dtos;

import br.com.medtech.ms_historico.entities.StatusConsulta;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ConsultaDTO(
    UUID id,
    UUID pacienteId,
    UUID medicoId,
    UUID enfermeiroId,
    OffsetDateTime dataHora,
    String observacoes,
    StatusConsulta status,
    OffsetDateTime criadaEm,
    OffsetDateTime atualizadaEm
) {}
