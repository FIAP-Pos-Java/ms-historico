package br.com.medtech.ms_historico.services;

import br.com.medtech.ms_historico.dtos.UsuarioDTO;
import br.com.medtech.ms_historico.entities.Usuario;
import br.com.medtech.ms_historico.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public void salvarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsByEmail(usuarioDTO.email())) {
            log.info("Usuário já existe no histórico: {}", usuarioDTO.email());
            return;
        }

        Usuario usuario = new Usuario(
            usuarioDTO.id(),
            usuarioDTO.nome(),
            usuarioDTO.email(),
            usuarioDTO.role()
        );

        usuarioRepository.save(usuario);
        log.info("Usuário salvo no histórico: {}", usuario.getId());
    }
}
