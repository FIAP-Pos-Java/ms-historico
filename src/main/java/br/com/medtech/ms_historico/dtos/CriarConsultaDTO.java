package br.com.medtech.ms_historico.dtos;

import br.com.medtech.ms_historico.entities.StatusConsulta;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CriarConsultaDTO(
    @NotNull UUID pacienteId,
    @NotNull UUID medicoId,
    UUID enfermeiroId,
    @NotNull LocalDateTime dataHora,
    String observacoes,
    StatusConsulta status
) {}
