package br.com.medtech.ms_historico.repositories;

import br.com.medtech.ms_historico.entities.Consulta;
import br.com.medtech.ms_historico.entities.StatusConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {
    
    List<Consulta> findByPacienteId(UUID pacienteId);
    
    List<Consulta> findByMedicoId(UUID medicoId);
    
    List<Consulta> findByEnfermeiroId(UUID enfermeiroId);
    
    List<Consulta> findByStatus(StatusConsulta status);
    
    List<Consulta> findByPacienteIdAndStatus(UUID pacienteId, StatusConsulta status);
    
    @Query("SELECT c FROM Consulta c WHERE c.dataConsulta >= :dataInicio AND c.dataConsulta <= :dataFim")
    List<Consulta> findByDataConsultaBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
    
    @Query("SELECT c FROM Consulta c WHERE c.pacienteId = :pacienteId AND c.dataConsulta >= :dataInicio")
    List<Consulta> findConsultasFuturasByPaciente(UUID pacienteId, LocalDateTime dataInicio);
}
