package br.ueg.musica.service;

import br.ueg.musica.dto.MusicasFavoritasGeneroDto;
import br.ueg.musica.model.MusicaModel;

import java.util.List;

public interface MusicaService {

    public void incluir(MusicaModel musicaModel);

    public MusicaModel alterar(MusicaModel musicaModel);

    public MusicaModel excluir(Long idMusica);

    public List<MusicaModel> listar();
    public MusicaModel pesquisarMusicaOuGeraErro(Long idMusica);

    public MusicaModel favoritarMusica (Long idMusica);

    public List<MusicasFavoritasGeneroDto> listarFavoritas ();
}
