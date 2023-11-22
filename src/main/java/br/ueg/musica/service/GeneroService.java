package br.ueg.musica.service;

import br.ueg.musica.model.GeneroModel;

import java.util.List;

public interface GeneroService {

    public void incluir(GeneroModel generoModel);

    public GeneroModel alterar(GeneroModel generoModel);

    public GeneroModel excluir(Long idGenero);

    public List<GeneroModel> listar();

    public GeneroModel pesquisarGeneroOuGeraErro(Long idGenero);
}
