package br.ueg.musica.service;

import br.ueg.musica.dto.MusicasFavoritasGeneroDto;
import br.ueg.musica.model.MusicaModel;
import br.ueg.prog.webi.api.service.CrudService;

import java.util.List;

public interface MusicaService extends CrudService<MusicaModel, Long> {

    public MusicaModel incluir(MusicaModel musicaModel);

    public MusicaModel alterar(MusicaModel musicaModel);

    public MusicaModel excluir(Long idMusica);

    public List<MusicaModel> listar();
    public MusicaModel pesquisarMusicaOuGeraErro(Long idMusica);

    public MusicaModel favoritarMusica (Long idMusica);

    public List<MusicasFavoritasGeneroDto> listarFavoritas ();
}
