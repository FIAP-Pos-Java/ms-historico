package br.com.medtech.ms_historico.dtos;

import br.com.medtech.ms_historico.entities.StatusConsulta;

import java.time.LocalDateTime;
import java.util.UUID;

public record AtualizarConsultaDTO(
    UUID medicoId,
    UUID enfermeiroId,
    LocalDateTime dataHora,
    String observacoes,
    StatusConsulta status
) {}
