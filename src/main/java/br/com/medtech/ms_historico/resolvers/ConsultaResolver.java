package br.com.medtech.ms_historico.resolvers;

import br.com.medtech.ms_historico.dtos.AtualizarConsultaDTO;
import br.com.medtech.ms_historico.dtos.ConsultaDTO;
import br.com.medtech.ms_historico.dtos.CriarConsultaDTO;
import br.com.medtech.ms_historico.entities.StatusConsulta;
import br.com.medtech.ms_historico.services.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ConsultaResolver {

    private final ConsultaService consultaService;

    @QueryMapping
    public List<ConsultaDTO> listarTodasConsultas() {
        return consultaService.listarTodasConsultas();
    }

    @QueryMapping
    public ConsultaDTO buscarConsultaPorId(@Argument UUID id) {
        return consultaService.buscarPorId(id);
    }

    @QueryMapping
    public List<ConsultaDTO> listarConsultasPorPaciente(@Argument UUID pacienteId) {
        return consultaService.listarConsultasPorPaciente(pacienteId);
    }

    @QueryMapping
    public List<ConsultaDTO> listarConsultasFuturasPorPaciente(@Argument UUID pacienteId) {
        return consultaService.listarConsultasFuturasPorPaciente(pacienteId);
    }

    @QueryMapping
    public List<ConsultaDTO> listarConsultasPorMedico(@Argument UUID medicoId) {
        return consultaService.listarConsultasPorMedico(medicoId);
    }

    @QueryMapping
    public List<ConsultaDTO> listarConsultasPorStatus(@Argument StatusConsulta status) {
        return consultaService.listarConsultasPorStatus(status);
    }

    @MutationMapping
    public ConsultaDTO criarConsulta(@Argument CriarConsultaDTO input) {
        return consultaService.criarConsulta(input);
    }

    @MutationMapping
    public ConsultaDTO atualizarConsulta(@Argument UUID id, @Argument AtualizarConsultaDTO input) {
        return consultaService.atualizarConsulta(id, input);
    }
}
