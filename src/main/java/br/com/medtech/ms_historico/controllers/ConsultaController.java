package br.com.medtech.ms_historico.controllers;

import br.com.medtech.ms_historico.dtos.AtualizarConsultaDTO;
import br.com.medtech.ms_historico.dtos.ConsultaDTO;
import br.com.medtech.ms_historico.dtos.CriarConsultaDTO;
import br.com.medtech.ms_historico.entities.StatusConsulta;
import br.com.medtech.ms_historico.services.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaDTO> criarConsulta(@Valid @RequestBody CriarConsultaDTO dto) {
        ConsultaDTO consulta = consultaService.criarConsulta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(consulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> atualizarConsulta(
            @PathVariable UUID id,
            @Valid @RequestBody AtualizarConsultaDTO dto) {
        ConsultaDTO consulta = consultaService.atualizarConsulta(id, dto);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listarTodasConsultas() {
        List<ConsultaDTO> consultas = consultaService.listarTodasConsultas();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> buscarConsultaPorId(@PathVariable UUID id) {
        ConsultaDTO consulta = consultaService.buscarPorId(id);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaDTO>> listarConsultasPorPaciente(@PathVariable UUID pacienteId) {
        List<ConsultaDTO> consultas = consultaService.listarConsultasPorPaciente(pacienteId);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/paciente/{pacienteId}/futuras")
    public ResponseEntity<List<ConsultaDTO>> listarConsultasFuturasPorPaciente(@PathVariable UUID pacienteId) {
        List<ConsultaDTO> consultas = consultaService.listarConsultasFuturasPorPaciente(pacienteId);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<ConsultaDTO>> listarConsultasPorMedico(@PathVariable UUID medicoId) {
        List<ConsultaDTO> consultas = consultaService.listarConsultasPorMedico(medicoId);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ConsultaDTO>> listarConsultasPorStatus(@PathVariable StatusConsulta status) {
        List<ConsultaDTO> consultas = consultaService.listarConsultasPorStatus(status);
        return ResponseEntity.ok(consultas);
    }
}
