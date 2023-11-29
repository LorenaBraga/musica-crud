package br.ueg.musica.service.impl;

import br.ueg.musica.dto.MusicasFavoritasDto;
import br.ueg.musica.dto.MusicasFavoritasGeneroDto;
import br.ueg.musica.model.MusicaModel;
import br.ueg.musica.repository.MusicaRepository;
import br.ueg.musica.service.MusicaService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MusicaServiceImpl extends BaseCrudService<MusicaModel, Long, MusicaRepository> implements MusicaService {

    @Override
    protected void prepararParaIncluir(MusicaModel entidade) {

    }

    @Override
    protected void validarDados(MusicaModel musica) {
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
    protected void validarCamposObrigatorios(MusicaModel entidade) {

    }


    public MusicaModel favoritarMusica (Long idMusica) {
        MusicaModel musicaModel = obterPeloId(idMusica);
        musicaModel.setFavorito(musicaModel.getFavorito() == null || !musicaModel.getFavorito());
        return super.alterar(musicaModel, idMusica);
    }

    @Override
    public List<MusicasFavoritasGeneroDto> listarFavoritas() {
        Optional<List<MusicaModel>> musicasFavoritas = repository.listarFavoritas();

        if (!musicasFavoritas.isPresent())
            return new ArrayList<>();

        Map<Long, MusicasFavoritasGeneroDto> map = new HashMap<>();

        if (musicasFavoritas != null && !musicasFavoritas.isEmpty()) {
            for (MusicaModel musica : musicasFavoritas.get()) {
                if (!map.containsKey(musica.getGenero().getId()))
                    map.put(musica.getGenero().getId(), new MusicasFavoritasGeneroDto(musica));

                map.get(musica.getGenero().getId()).getMusicasfavoritas().add(new MusicasFavoritasDto(musica));
            }
        }
        return map.values().stream().toList();
    }

}
