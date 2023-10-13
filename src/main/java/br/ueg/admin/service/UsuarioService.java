package br.ueg.admin.service;

import br.ueg.admin.model.UsuarioModel;
import br.ueg.admin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioModel incluir(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    public UsuarioModel buscarPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow(() -> new IllegalArgumentException("Usúario não encontrado"));
    }
}
