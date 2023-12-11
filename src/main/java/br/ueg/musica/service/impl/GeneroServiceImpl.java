package br.ueg.musica.service.impl;

import br.ueg.musica.model.Genero;
import br.ueg.musica.repository.GeneroRepository;
import br.ueg.musica.service.GeneroService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GeneroServiceImpl extends BaseCrudService<Genero, Long, GeneroRepository> implements GeneroService {

    @Override
    protected void prepararParaIncluir(Genero entidade) {

    }

    @Override
    protected void validarDados(Genero generoModel) {

    }

    @Override
    protected void validarCamposObrigatorios(Genero generoModel) {
        if (Objects.isNull(generoModel)) {
            throw new IllegalArgumentException("Campo precisa ser preenchida");
        }
        if (generoModel.getNome().isEmpty()) {
            throw new IllegalArgumentException(
                    "Campo nome não foi preenchido"
            );
        }
    }
}
