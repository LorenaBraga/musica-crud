package br.ueg.musica.service;

import br.ueg.musica.model.MusicaModel;

import java.util.List;

public interface MusicaService {

    public void incluir(MusicaModel musicaModel);

    public MusicaModel alterar(MusicaModel musicaModel);

    public MusicaModel excluir(Long idMusica);

    public List<MusicaModel> listar();
}
