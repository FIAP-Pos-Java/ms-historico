package br.com.medtech.ms_historico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_consultas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID pacienteId;

    @Column(nullable = false)
    private UUID medicoId;

    private UUID enfermeiroId;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(length = 1000)
    private String observacoes;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    @Column(nullable = false)
    private LocalDateTime criadaEm;

    private LocalDateTime atualizadaEm;

    @PrePersist
    protected void onCreate() {
        criadaEm = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        atualizadaEm = LocalDateTime.now();
    }
}
