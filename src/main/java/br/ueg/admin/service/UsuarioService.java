package br.ueg.admin.service;

import br.ueg.admin.exception.ModuleAdminMessageCode;
import br.ueg.admin.mapper.UsuarioMapper;
import br.ueg.admin.model.UsuarioModel;
import br.ueg.admin.repository.UsuarioRepository;
import br.ueg.prog.webi.api.dto.AuthDTO;
import br.ueg.prog.webi.api.dto.UsuarioSenhaDTO;
import br.ueg.prog.webi.api.exception.ApiMessageCode;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.service.UserPasswordService;
import br.ueg.prog.webi.api.util.CollectionUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private DataSource dataSource;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private InicializarService inicializarService;

    /**
     * Persiste os dados do {@link UsuarioModel}.
     *
     * @param usuario
     * @return
     */
    public UsuarioModel salvar(UsuarioModel usuario) {
        validarCamposObrigatorios(usuario);
        validarUsuarioDuplicadoPorLogin(usuario);

        if (usuario.getId() == null) {

            LocalDate dataCadastro = LocalDate.now();
            usuario.setSenha(new BCryptPasswordEncoder().encode("123456"));

        } else {
            UsuarioModel vigente = getById(usuario.getId());

            usuario.setSenha(vigente.getSenha());
        }

        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

    /**
     * Verifica a existencia de {@link UsuarioModel} acordo com o 'login' informado.
     *
     * @param usuario
     */
    private void validarUsuarioDuplicadoPorLogin(final UsuarioModel usuario) {
        Long count = usuarioRepository.countByLogin(usuario.getLogin());

        if ( (count > BigDecimal.ONE.longValue() && usuario.getId()!=null) ||
                (count > BigDecimal.ZERO.longValue() && usuario.getId()==null)
        ) {
            throw new BusinessException(ModuleAdminMessageCode.ERRO_LOGIN_DUPLICADO);
        }
    }


    /**
     * Verifica se os campos obrigatorios de {@link UsuarioModel} foram preenchidos.
     *
     * @param usuario
     */
    private void validarCamposObrigatorios(final UsuarioModel usuario) {
        boolean invalido = Boolean.FALSE;

        if (Strings.isEmpty(usuario.getLogin())) {
            invalido = Boolean.TRUE;
        }

        if (invalido) {
            throw new BusinessException(ApiMessageCode.ERRO_CAMPOS_OBRIGATORIOS);
        }
    }

    /**
     * Retorna a instância do {@link UsuarioModel} conforme o 'login' informado.
     *
     * @param login
     * @return
     */
    public UsuarioModel getByLogin(String login) {
        UsuarioModel byLogin = usuarioRepository.findByLogin(login);
        if(Objects.isNull(byLogin)){
            return null;
        }
        Optional<UsuarioModel> byIdFetch = usuarioRepository.findByIdFetch(byLogin.getId());
        return byIdFetch.get();
    }


    /**
     * Retorna a instância de {@link UsuarioModel} conforme o 'id' informado.
     *
     * @param id -
     * @return -
     */
    public UsuarioModel getById(final Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    /**
     * Retorna a instância de {@link UsuarioModel} conforme o 'id' informado.
     *
     * @param id
     * @return
     */
    public UsuarioModel getByIdFetch(final Long id) {
        return usuarioRepository.findByIdFetch(id).orElse(null);
    }

    /**
     * Valida se as senhas foram preenchidas e se são iguais
     *
     * @param redefinirSenhaDTO -
     */
    private void validarConformidadeSenha(final UsuarioSenhaDTO redefinirSenhaDTO) {
        if (Strings.isEmpty(redefinirSenhaDTO.getNovaSenha()) || Strings.isEmpty(redefinirSenhaDTO.getConfirmarSenha())) {
            throw new BusinessException(ApiMessageCode.ERRO_CAMPOS_OBRIGATORIOS);
        }

        if (!redefinirSenhaDTO.getNovaSenha().equals(redefinirSenhaDTO.getConfirmarSenha())) {
            throw new BusinessException(ModuleAdminMessageCode.ERRO_SENHAS_DIFERENTES);
        }
    }

    /**
     * Valida se a senha corrente foi preenchida e corresponde a senha armazenada no
     * keycloak.
     *
     * @param usuario -
     * @param senhaAntiga -
     */
    private void validarSenhaCorrente(UsuarioModel usuario, String senhaAntiga) {
        if (Strings.isEmpty(senhaAntiga)) {
            throw new BusinessException(ApiMessageCode.ERRO_CAMPOS_OBRIGATORIOS);
        }

        AuthDTO authDTO = new AuthDTO();
        authDTO.setLogin(usuario.getLogin());
        authDTO.setSenha(senhaAntiga);
        if (!UserPasswordService.loginByPassword(this.usuarioMapper.toCredentialDTO(usuario), authDTO)) {
            throw new BusinessException(ModuleAdminMessageCode.ERRO_SENHA_ANTERIOR_INCORRETA);
        }
    }

    /**
     * Realiza a inclusão ou alteração de senha do {@link UsuarioModel}.
     *
     * @param usuarioSenhaDTO -
     */
    public UsuarioModel redefinirSenha(final UsuarioSenhaDTO usuarioSenhaDTO) {
        UsuarioModel usuario = getById(usuarioSenhaDTO.getIdUsuario());

        validarConformidadeSenha(usuarioSenhaDTO);

        if (!usuarioSenhaDTO.isAtivacao() && !usuarioSenhaDTO.isRecuperacao()) {
            validarSenhaCorrente(usuario, usuarioSenhaDTO.getSenhaAntiga());
        }
        usuario.setSenha(usuarioSenhaDTO.getNovaSenha());
        return usuarioRepository.save(usuario);
    }

    /**
     * Retorna a instância de {@link UsuarioModel} conforme o 'login' informado.
     *
     * @param login
     * @return
     */
    public UsuarioModel findByLoginUsuario(final String login) {
        return usuarioRepository.findByLogin(login);
    }

    /**
     * Retorna a instância do {@link UsuarioModel} conforme o 'login' informado
     * e que não tenha o 'id' informado.
     *
     * @param login
     * @param id
     * @return
     */
    public UsuarioModel findByLoginUsuarioAndNotId(final String login, final Long id) {
        return usuarioRepository.findByLoginAndNotId(login, id);
    }

    /**
     * Verifica se o Login informado é tem um valor válido.
     *
     * @param login
     * @return
     */
    private boolean isLoginValido(final String login) {
        boolean valido = false;

        //Colocar outras vaidações se necessário
        if (Strings.isNotEmpty(login)) {
            valido = true;
        }

        if(Strings.isNotEmpty(login) && login.length()>3){
            valido = true;
        }
        return valido;
    }

    /**
     * Verifica se o login informado é válido e se está em uso.
     *
     * @param login
     */
    public void validarLogin(final String login) {
        validarLogin(login, null);
    }

    /**
     * Verifica se o Login informado é válido e se está em uso.
     *
     * @param login
     * @param id
     */
    public void validarLogin(final String login, final Long id) {
        if (!isLoginValido(login)) {
            throw new BusinessException(ModuleAdminMessageCode.ERRO_LOGIN_INVALIDO);
        }

        UsuarioModel usuario;

        if (id == null) {
            usuario = findByLoginUsuario(login);
        } else {
            usuario = findByLoginUsuarioAndNotId(login, id);
        }

        if (usuario != null) {
            throw new BusinessException(ModuleAdminMessageCode.ERRO_LOGIN_EM_USO);
        }
    }
    public void inicializar(String senha) {
        if(Strings.isNotEmpty(senha) && senha.equals("inicializar")){
            inicializarService.inicializar();
        }else{
            throw new BusinessException(ModuleAdminMessageCode.ERRO_LOGIN_INVALIDO);
        }
    }
}
