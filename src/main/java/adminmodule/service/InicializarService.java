package adminmodule.service;

import adminmodule.model.*;
import adminmodule.model.enums.StatusAtivoInativo;
import adminmodule.repository.GrupoRepository;
import adminmodule.repository.ModuloSistemaRepository;
import adminmodule.repository.UsuarioRepository;
import br.ueg.musica.model.Genero;
import br.ueg.musica.model.Musica;
import br.ueg.musica.repository.GeneroRepository;
import br.ueg.musica.repository.MusicaRepository;
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
    private GrupoRepository grupoRepository;
    @Autowired
    private ModuloSistemaRepository moduloSistemaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private MusicaRepository musicaRepository;

    public void inicializar(){
        LOG.info("initiateDemoInstance");
        Usuario usuario = getNewUsuario();
        usuario = usuarioRepository.findByLogin(usuario.getLogin());
        //Caso já tenha usuário não executa novamente.
        if(Objects.nonNull(usuario)){
            return;
        }

        ModuloSistema moduloUsuario = createModuloCrud("USUARIO", "Manter Usuário");

        ModuloSistema moduloGrupo = createModuloCrud("GRUPO", "Manter Grupo");

        Grupo grupo = createGrupoAdmin(Arrays.asList(moduloUsuario, moduloGrupo));

        createUsuarioAdmin(grupo);

        List<Genero> generos = createGeneros();

        createMusica(generos);
    }

    private List<Genero> createGeneros() {
        List<String> listaGenero = Arrays.asList("Rock", "Pop", "Indie");
        for (String s : listaGenero) {
            generoRepository.save(new Genero(s));
        }

        return generoRepository.findAll();
    }

    private void createMusica(List<Genero> generos) {
        Map<String, Genero> mapGeneros = new HashMap<>();
        generos.forEach(genero -> mapGeneros.putIfAbsent(genero.getNome(), genero));

        musicaRepository.save(new Musica("Linkin Park", "Numb", "Numb", 42, LocalDate.now(), true, mapGeneros.get("Rock")));
        musicaRepository.save(new Musica("Evanescence", "Bring Me To Life", "Evanescence", 123, LocalDate.now(), true, mapGeneros.get("Rock")));
        musicaRepository.save(new Musica("Nickelback", "How You Remind Me", "Nickelback", 412, LocalDate.now(), false,mapGeneros.get("Rock")));
        musicaRepository.save(new Musica("Green Day", "Boulevard Of Broken Dreams", "Green Day", 53, LocalDate.now(), true, mapGeneros.get("Rock")));
        musicaRepository.save(new Musica("Brynn Elliott", "Terrified", "Terrified", 61, LocalDate.now(), true, mapGeneros.get("Pop")));
        musicaRepository.save(new Musica("Harry Styles", "As It Was", "Harry Styles", 123, LocalDate.now(), false, mapGeneros.get("Pop")));
        musicaRepository.save(new Musica("The Weeknd", "Save Your Tears", "The Weeknd", 53, LocalDate.now(), true, mapGeneros.get("Pop")));
        musicaRepository.save(new Musica("Maroon 5", "Girls Like You", "Maroon 5", 221, LocalDate.now(), false, mapGeneros.get("Pop")));
        musicaRepository.save(new Musica("The Lumineers", "Sleep On The Floor", "The Lumineers", 52, LocalDate.now(), true, mapGeneros.get("Indie")));
        musicaRepository.save(new Musica("Ghost", "Mary On A Cross", "Ghost", 123, LocalDate.now(), true, mapGeneros.get("Indie")));
        musicaRepository.save(new Musica("Lana Del Rey", "Brooklyn Baby", "Lana Del Rey", 53, LocalDate.now(), true, mapGeneros.get("Indie")));
        musicaRepository.save(new Musica("Pink Floyd", "Wish You Were Here", "Pink Floyd", 412, LocalDate.now(), true, mapGeneros.get("Indie")));
    }

    private ModuloSistema createModuloCrud(String moduloMNemonico, String moduloNome) {
        ModuloSistema moduloUsuario = new ModuloSistema();

        moduloUsuario.setMnemonico(moduloMNemonico);
        moduloUsuario.setNome(moduloNome);
        moduloUsuario.setStatus(StatusAtivoInativo.ATIVO);
        moduloUsuario = moduloSistemaRepository.save(moduloUsuario);

        final ModuloSistema lModuloUsuario = moduloUsuario;
        Set<Funcionalidade> funcionaldiades = getFuncionalidadesCrud();

/*        funcionaldiades.stream().map(
                funcionalidade -> {
                    funcionalidade.setModulo(lModuloUsuario);
                    return funcionalidade;
                }).collect(Collectors.toSet());
        // equivalente com for*/
        for(Funcionalidade funcionalidade: funcionaldiades){
            funcionalidade.setModuloSistema(moduloUsuario);
        }

        moduloUsuario.setFuncionalidades(funcionaldiades);
        moduloUsuario = moduloSistemaRepository.save(moduloUsuario);
        return moduloUsuario;
    }

    /**
     * retorna Funcionalidades padrão de um CRRUD
     * @return
     */
    private Set<Funcionalidade> getFuncionalidadesCrud() {
        Set<Funcionalidade> funcionalidades = new HashSet<>();

        Funcionalidade fManter = new Funcionalidade();
        fManter.setMnemonico("INCLUIR");
        fManter.setNome("Incluir");
        fManter.setStatus(StatusAtivoInativo.ATIVO);
        funcionalidades.add(fManter);

        fManter = new Funcionalidade();
        fManter.setMnemonico("ALTERAR");
        fManter.setNome("Alterar");
        fManter.setStatus(StatusAtivoInativo.ATIVO);
        funcionalidades.add(fManter);

        fManter = new Funcionalidade();
        fManter.setMnemonico("ATIVAR_INATIVAR");
        fManter.setNome("Ativar/Inativar");
        fManter.setStatus(StatusAtivoInativo.ATIVO);
        funcionalidades.add(fManter);

        fManter = new Funcionalidade();
        fManter.setMnemonico("PESQUISAR");
        fManter.setNome("Pesquisar");
        fManter.setStatus(StatusAtivoInativo.ATIVO);
        funcionalidades.add(fManter);

        fManter = new Funcionalidade();
        fManter.setMnemonico("VISUALIZAR");
        fManter.setNome("Visualizar");
        fManter.setStatus(StatusAtivoInativo.ATIVO);
        funcionalidades.add(fManter);
        return funcionalidades;
    }

    private Grupo createGrupoAdmin(List<ModuloSistema> modulos) {
        Grupo grupo = new Grupo();
        grupo.setNome("Administradores");
        grupo.setDescricao("Grupo de Administradores");
        grupo.setStatus(StatusAtivoInativo.ATIVO);
        grupo = grupoRepository.save(grupo);
        final Grupo lGrupo = grupo;
        grupo.setGrupoFuncionalidades(new HashSet<>());

        modulos.forEach(modulo -> {
            lGrupo.getGrupoFuncionalidades().addAll(
                    modulo.getFuncionalidades().stream().map(
                            funcionalidade -> new GrupoFuncionalidade(null, lGrupo, funcionalidade)
                    ).collect(Collectors.toSet())
            );
        });

        grupoRepository.save(grupo);
        return grupo;
    }
    private void createUsuarioAdmin(Grupo grupo) {
        Usuario usuario = getNewUsuario();

        usuario = usuarioRepository.save(usuario);

        Set<UsuarioGrupo> usuarioGrupos = new HashSet<>();
        usuarioGrupos.add(new UsuarioGrupo(null,usuario,grupo));
        usuario.setGrupos(usuarioGrupos);
        usuario = usuarioRepository.save(usuario);
    }

    private static Usuario getNewUsuario() {
        Usuario usuario = new Usuario();
        usuario.setStatus(StatusAtivoInativo.ATIVO);
        usuario.setDataCadastrado(LocalDate.now());
        usuario.setDataAtualizado(LocalDate.now());
        usuario.setTelefones(new HashSet<>());
        usuario.setLogin("admin");
        usuario.setNome("Administrador");
        usuario.setEmail("admin@teste.com.br");
        usuario.setSenha(new BCryptPasswordEncoder().encode("admin"));
        return usuario;
    }

}
