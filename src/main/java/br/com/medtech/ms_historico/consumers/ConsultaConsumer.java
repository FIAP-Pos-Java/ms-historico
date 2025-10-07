package br.com.medtech.ms_historico.consumers;

import br.com.medtech.ms_historico.config.RabbitMQConfiguration;
import br.com.medtech.ms_historico.dtos.ConsultaDTO;
import br.com.medtech.ms_historico.services.ConsultaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConsultaConsumer {

    private final ConsultaService consultaService;

    @RabbitListener(queues = RabbitMQConfiguration.CONSULTAS_QUEUE)
    public void receberConsulta(ConsultaDTO consultaDTO) {
        log.info("Consulta recebida via RabbitMQ: {}", consultaDTO);
        try {
            consultaService.salvarConsultaRecebida(consultaDTO);
            log.info("Consulta salva no histórico com sucesso: {}", consultaDTO.id());
        } catch (Exception e) {
            log.error("Erro ao salvar consulta no histórico: {}", e.getMessage());
        }
    }
}
