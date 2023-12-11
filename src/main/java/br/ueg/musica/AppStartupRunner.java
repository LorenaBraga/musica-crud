package br.ueg.musica;

import adminmodule.service.InicializarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class AppStartupRunner implements ApplicationRunner {

    public static final String NONE="none";
    public static final String CREATE_DROP="create-drop";
    public static final String CREATE = "create";
    public static final String UPDATE = "update";

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    private static final Logger LOG =
            LoggerFactory.getLogger(AppStartupRunner.class);


    @Autowired
    private InicializarService inicializarService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            this.inicializarService.inicializar();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
