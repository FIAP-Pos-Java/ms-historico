package br.com.medtech.ms_historico.consumers;

import br.com.medtech.ms_historico.config.RabbitMQConfiguration;
import br.com.medtech.ms_historico.dtos.UsuarioDTO;
import br.com.medtech.ms_historico.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UsuarioConsumer {

    private final UsuarioService usuarioService;

    @RabbitListener(queues = RabbitMQConfiguration.USUARIOS_QUEUE)
    public void receberUsuario(UsuarioDTO usuarioDTO) {
        log.info("Usuário recebido via RabbitMQ: {}", usuarioDTO);
        try {
            usuarioService.salvarUsuario(usuarioDTO);
            log.info("Usuário salvo com sucesso: {}", usuarioDTO.id());
        } catch (Exception e) {
            log.error("Erro ao salvar usuário: {}", e.getMessage());
        }
    }
}
