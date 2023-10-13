package br.ueg.musica;

import br.ueg.genero.model.GeneroModel;
import br.ueg.genero.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private GeneroService generoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        GeneroModel generoModel = new GeneroModel();
        generoModel.setNome("Sertanejo");
        generoService.incluir(generoModel);
    }
}
