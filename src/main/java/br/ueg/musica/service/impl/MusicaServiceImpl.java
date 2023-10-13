package br.ueg.musica.service.impl;

import br.ueg.musica.dto.MusicasFavoritasDto;
import br.ueg.musica.dto.MusicasFavoritasGeneroDto;
import br.ueg.musica.model.MusicaModel;
import br.ueg.musica.repository.MusicaRepository;
import br.ueg.musica.service.MusicaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class MusicaServiceImpl implements MusicaService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MusicaRepository musicaRepository;

    public void incluir(MusicaModel musicaModel) {
        this.validarCamposObrigatorios(musicaModel);
        musicaRepository.save(musicaModel);
    }

    private void validarCamposObrigatorios(MusicaModel musica) {
        if (Objects.isNull(musica)) {
            throw new IllegalArgumentException("Campo precisa ser preenchida");
        }
        List<String> campoVazio = new ArrayList<>();
        if (Strings.isEmpty(musica.getNomeMusica())) {
            campoVazio.add("Nome musica");
        }
        if (Strings.isEmpty(musica.getNomeBanda())) {
            campoVazio.add("Nome banda");
        }
        if (Strings.isEmpty(musica.getNomeAlbum())) {
            campoVazio.add("Nome album");
        }
        if (musica.getDuracao() == null) {
            campoVazio.add("Duração");
        }
        if (musica.getDataLancamento() == null) {
            campoVazio.add("Data lançamento");
        }
        if (!campoVazio.isEmpty()) {
            throw new IllegalArgumentException(
                    "Campos obrigatórios não preenchidos (" +
                            String.join(",", campoVazio) + ")"
            );
        }

    }

    @Override
    public MusicaModel alterar(MusicaModel musicaModel) {
        this.pesquisarMusicaOuGeraErro(musicaModel.getId());
        this.validarCamposObrigatorios(musicaModel);
        return musicaRepository.save(musicaModel);
    }

    @Override
    public MusicaModel excluir(Long idMusica) {
        MusicaModel excluirMusica = this.pesquisarMusicaOuGeraErro(idMusica);
        this.musicaRepository.delete(excluirMusica);
        return excluirMusica;
    }

    @Override
    public List<MusicaModel> listar() {
        return musicaRepository.findAll();
    }

    public MusicaModel pesquisarMusicaOuGeraErro(Long idMusica) {
        MusicaModel musicaModel = musicaRepository.findById(idMusica).orElseThrow(() -> new IllegalArgumentException("Música não encontrada"));
        return musicaModel;
    }

    public MusicaModel favoritarMusica (Long idMusica) {
        MusicaModel musicaModel = pesquisarMusicaOuGeraErro(idMusica);
        musicaModel.setFavorito(musicaModel.getFavorito() == null || !musicaModel.getFavorito());
        return musicaRepository.save(musicaModel);
    }

    @Override
    public List<MusicasFavoritasGeneroDto> listarFavoritas() {
        Map<Long, MusicasFavoritasGeneroDto> map = new HashMap<>();
        StringBuilder jpql = new StringBuilder();
        jpql.append(" SELECT musica from MusicaModel musica");
        jpql.append(" JOIN musica.genero genero");
        jpql.append(" WHERE musica.favorito = true");

        TypedQuery<MusicaModel> query = entityManager.createQuery(jpql.toString(), MusicaModel.class);
        List<MusicaModel> musicas = query.getResultList();
        if (musicas != null && !musicas.isEmpty()) {
            for (MusicaModel musica : musicas) {
                if (!map.containsKey(musica.getGenero().getId()))
                    map.put(musica.getGenero().getId(), new MusicasFavoritasGeneroDto(musica));

                map.get(musica.getGenero().getId()).getMusicasfavoritas().add(new MusicasFavoritasDto(musica));
            }
        }
        return map.values().stream().toList();
    }

}
