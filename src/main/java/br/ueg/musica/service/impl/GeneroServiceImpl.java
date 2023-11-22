package br.ueg.musica.service.impl;

import br.ueg.musica.model.GeneroModel;
import br.ueg.musica.repository.GeneroRepository;
import br.ueg.musica.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GeneroServiceImpl implements GeneroService {


    @Autowired
    private GeneroRepository generoRepository;
    @Override
    public void incluir(GeneroModel generoModel) {
        this.validarCamposObrigatorios(generoModel);
        generoRepository.save(generoModel);
    }

    private void validarCamposObrigatorios(GeneroModel generoModel) {
        if (Objects.isNull(generoModel)) {
            throw new IllegalArgumentException("Campo precisa ser preenchida");
        }
        if (generoModel.getNome().isEmpty()) {
            throw new IllegalArgumentException(
                    "Campo nome não foi preenchido"
            );
        }
    }

    @Override
    public GeneroModel alterar(GeneroModel generoModel) {
        this.pesquisarGeneroOuGeraErro(generoModel.getId());
        this.validarCamposObrigatorios(generoModel);
        return generoRepository.save(generoModel);
    }

    @Override
    public GeneroModel excluir(Long idGenero) {
        GeneroModel excluirGenero = this.pesquisarGeneroOuGeraErro(idGenero);
        this.generoRepository.delete(excluirGenero);
        return excluirGenero;
    }

    @Override
    public List<GeneroModel> listar() {return  generoRepository.findAll();
    }

    @Override
    public GeneroModel pesquisarGeneroOuGeraErro(Long idGenero) {
        return generoRepository.findById(idGenero).orElseThrow(() -> new IllegalArgumentException("Genero não encontrado"));
    }
}
