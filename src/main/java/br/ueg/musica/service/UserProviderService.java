package br.ueg.musica.service;

import br.ueg.admin.mapper.UsuarioMapper;
import br.ueg.admin.model.UsuarioModel;
import br.ueg.admin.service.UsuarioService;
import br.ueg.prog.webi.api.dto.CredencialDTO;
import br.ueg.prog.webi.api.dto.UsuarioSenhaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserProviderService implements br.ueg.prog.webi.api.service.UserProviderService {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;
    @Override
    public CredencialDTO getCredentialByLogin(String username) {
        UsuarioModel byLogin = this.usuarioService.getByLogin(username);
        CredencialDTO credencialDTO = this.usuarioMapper.toCredentialDTO(byLogin);
//        credencialDTO.setRoles(grupoService.getRolesByUsuario(byLogin.getId()));
        return credencialDTO;
    }

    @Override
    public CredencialDTO redefinirSenha(UsuarioSenhaDTO usuarioSenhaDTO) {
        return null;
        //return usuarioMapper.toCredentialDTO(this.usuarioService.redefinirSenha(usuarioSenhaDTO));
    }

    @Override
    public CredencialDTO getCredentialByEmail(String email) {
        return this.usuarioMapper.toCredentialDTO(this.usuarioService.findByLoginUsuario(email));
    }
}
