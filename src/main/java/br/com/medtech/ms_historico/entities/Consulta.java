package br.com.medtech.ms_historico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_consulta_historico")
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
    private LocalDateTime dataConsulta;

    @Column(length = 1000)
    private String observacoes;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    @Column(nullable = false)
    private OffsetDateTime criadaEm;

    private OffsetDateTime atualizadaEm;

    @PrePersist
    protected void onCreate() {
        criadaEm = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        atualizadaEm = OffsetDateTime.now();
    }
}
