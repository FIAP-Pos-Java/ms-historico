package br.com.medtech.ms_historico.services;

import br.com.medtech.ms_historico.dtos.AtualizarConsultaDTO;
import br.com.medtech.ms_historico.dtos.ConsultaDTO;
import br.com.medtech.ms_historico.dtos.CriarConsultaDTO;
import br.com.medtech.ms_historico.entities.Consulta;
import br.com.medtech.ms_historico.entities.StatusConsulta;
import br.com.medtech.ms_historico.repositories.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    @Transactional
    public ConsultaDTO criarConsulta(CriarConsultaDTO dto) {
        Consulta consulta = new Consulta();
        consulta.setPacienteId(dto.pacienteId());
        consulta.setMedicoId(dto.medicoId());
        consulta.setEnfermeiroId(dto.enfermeiroId());
        consulta.setDataHora(dto.dataHora());
        consulta.setObservacoes(dto.observacoes());
        consulta.setStatus(dto.status() != null ? dto.status() : StatusConsulta.AGENDADA);

        consulta = consultaRepository.save(consulta);
        log.info("Consulta criada: {}", consulta.getId());

        return toDTO(consulta);
    }

    @Transactional
    public void salvarConsultaRecebida(ConsultaDTO consultaDTO) {
        Consulta consulta = new Consulta();
        consulta.setId(consultaDTO.id());
        consulta.setPacienteId(consultaDTO.pacienteId());
        consulta.setMedicoId(consultaDTO.medicoId());
        consulta.setEnfermeiroId(consultaDTO.enfermeiroId());
        consulta.setDataHora(consultaDTO.dataHora());
        consulta.setObservacoes(consultaDTO.observacoes());
        consulta.setStatus(consultaDTO.status());

        consultaRepository.save(consulta);
        log.info("Consulta recebida salva no histórico: {}", consulta.getId());
    }

    @Transactional
    public ConsultaDTO atualizarConsulta(UUID id, AtualizarConsultaDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        if (dto.medicoId() != null) {
            consulta.setMedicoId(dto.medicoId());
        }
        if (dto.enfermeiroId() != null) {
            consulta.setEnfermeiroId(dto.enfermeiroId());
        }
        if (dto.dataHora() != null) {
            consulta.setDataHora(dto.dataHora());
        }
        if (dto.observacoes() != null) {
            consulta.setObservacoes(dto.observacoes());
        }
        if (dto.status() != null) {
            consulta.setStatus(dto.status());
        }

        consulta = consultaRepository.save(consulta);
        log.info("Consulta atualizada: {}", consulta.getId());

        return toDTO(consulta);
    }

    public List<ConsultaDTO> listarTodasConsultas() {
        return consultaRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public List<ConsultaDTO> listarConsultasPorPaciente(UUID pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId).stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public List<ConsultaDTO> listarConsultasPorMedico(UUID medicoId) {
        return consultaRepository.findByMedicoId(medicoId).stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public List<ConsultaDTO> listarConsultasFuturasPorPaciente(UUID pacienteId) {
        return consultaRepository.findConsultasFuturasByPaciente(pacienteId, OffsetDateTime.now()).stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public List<ConsultaDTO> listarConsultasPorStatus(StatusConsulta status) {
        return consultaRepository.findByStatus(status).stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public ConsultaDTO buscarPorId(UUID id) {
        return consultaRepository.findById(id)
            .map(this::toDTO)
            .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    }

    private ConsultaDTO toDTO(Consulta consulta) {
        return new ConsultaDTO(
            consulta.getId(),
            consulta.getPacienteId(),
            consulta.getMedicoId(),
            consulta.getEnfermeiroId(),
            consulta.getDataHora(),
            consulta.getObservacoes(),
            consulta.getStatus(),
            consulta.getCriadaEm(),
            consulta.getAtualizadaEm()
        );
    }
}
