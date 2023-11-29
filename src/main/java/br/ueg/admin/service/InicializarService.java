package br.ueg.admin.service;

import br.ueg.admin.model.FuncionalidadeModel;
import br.ueg.admin.model.ModuloModel;
import br.ueg.admin.model.UsuarioModel;
import br.ueg.admin.model.enums.StatusAtivoInativo;
import br.ueg.admin.repository.ModuloRepository;
import br.ueg.admin.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class InicializarService {
    private static final Logger LOG =
            LoggerFactory.getLogger(InicializarService.class);

    @Autowired
    private ModuloRepository moduloService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void inicializar(){
        LOG.info("initiateDemoInstance");
        UsuarioModel usuario = getNewUsuario();
        usuario = usuarioRepository.findByLogin(usuario.getLogin());
        //Caso já tenha usuário não executa novamente.
        if(Objects.nonNull(usuario)){
            return;
        }

        usuario = usuarioRepository.save(getNewUsuario());

    }

    private ModuloModel createModuloCrud(String moduloMNemonico, String moduloNome) {
        ModuloModel moduloUsuario = new ModuloModel();

        moduloUsuario.setMnemonico(moduloMNemonico);
        moduloUsuario.setNome(moduloNome);
        moduloUsuario.setStatus(StatusAtivoInativo.ATIVO);
        moduloUsuario = moduloService.save(moduloUsuario);

        final ModuloModel lModuloUsuario = moduloUsuario;
        Set<FuncionalidadeModel> funcionaldiades = getFuncionalidadesCrud();

/*        funcionaldiades.stream().map(
                funcionalidade -> {
                    funcionalidade.setModulo(lModuloUsuario);
                    return funcionalidade;
                }).collect(Collectors.toSet());
        // equivalente com for*/
        for(FuncionalidadeModel funcionalidade: funcionaldiades){
            funcionalidade.setModuloSistema(moduloUsuario);
        }

        moduloUsuario.setFuncionalidades(funcionaldiades);
        moduloUsuario = moduloService.save(moduloUsuario);
        return moduloUsuario;
    }

    /**
     * retorna Funcionalidades padrão de um CRRUD
     * @return
     */
    private Set<FuncionalidadeModel> getFuncionalidadesCrud() {
        Set<FuncionalidadeModel> funcionalidades = new HashSet<>();

        FuncionalidadeModel fManter = new FuncionalidadeModel();
        fManter.setMnemonico("INCLUIR");
        fManter.setNome("Incluir");
        fManter.setStatus(StatusAtivoInativo.ATIVO);
        funcionalidades.add(fManter);

        fManter = new FuncionalidadeModel();
        fManter.setMnemonico("ALTERAR");
        fManter.setNome("Alterar");
        fManter.setStatus(StatusAtivoInativo.ATIVO);
        funcionalidades.add(fManter);

        fManter = new FuncionalidadeModel();
        fManter.setMnemonico("ATIVAR_INATIVAR");
        fManter.setNome("Ativar/Inativar");
        fManter.setStatus(StatusAtivoInativo.ATIVO);
        funcionalidades.add(fManter);

        fManter = new FuncionalidadeModel();
        fManter.setMnemonico("PESQUISAR");
        fManter.setNome("Pesquisar");
        fManter.setStatus(StatusAtivoInativo.ATIVO);
        funcionalidades.add(fManter);

        fManter = new FuncionalidadeModel();
        fManter.setMnemonico("VISUALIZAR");
        fManter.setNome("Visualizar");
        fManter.setStatus(StatusAtivoInativo.ATIVO);
        funcionalidades.add(fManter);
        return funcionalidades;
    }

    private static UsuarioModel getNewUsuario() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setLogin("admin");
        usuario.setNome("Administrador");
        usuario.setEmail("admin@teste.com.br");
        usuario.setStatus(StatusAtivoInativo.ATIVO);
        usuario.setSenha(new BCryptPasswordEncoder().encode("admin"));
        return usuario;
    }



}
