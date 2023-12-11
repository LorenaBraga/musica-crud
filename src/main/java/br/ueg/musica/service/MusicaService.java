package br.ueg.musica.service;

import br.ueg.musica.dto.MusicasFavoritasGeneroDto;
import br.ueg.musica.model.Musica;
import br.ueg.prog.webi.api.service.CrudService;

import java.util.List;

public interface MusicaService extends CrudService<Musica, Long> {

    public Musica favoritarMusica (Long idMusica);

    public List<MusicasFavoritasGeneroDto> listarFavoritas ();
}
