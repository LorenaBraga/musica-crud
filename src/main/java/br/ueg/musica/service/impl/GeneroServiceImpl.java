package br.ueg.musica.service.impl;

import br.ueg.musica.model.GeneroModel;
import br.ueg.musica.repository.GeneroRepository;
import br.ueg.musica.service.GeneroService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GeneroServiceImpl extends BaseCrudService<GeneroModel, Long, GeneroRepository> implements GeneroService {

    @Override
    protected void prepararParaIncluir(GeneroModel entidade) {

    }

    @Override
    protected void validarDados(GeneroModel generoModel) {

    }

    @Override
    protected void validarCamposObrigatorios(GeneroModel generoModel) {
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
