package br.ueg.musica;

import br.ueg.admin.service.InicializarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"br.ueg.musica.*",
		"br.ueg.admin.*",
		"br.ueg.prog.webi.api.*", "br.ueg.prog.webi.*"
})
@EnableJpaRepositories(basePackages = {
		"br.ueg.musica.*",
		"br.ueg.admin.*"
})
@EntityScan(basePackageClasses = {
		Jsr310JpaConverters.class }, basePackages = { "br.ueg.musica.*", "br.ueg.admin.*"})
public class MusicaApplication implements ApplicationRunner {

	@Autowired
	private InicializarService inicializarService;

	public static void main(String[] args) {
		SpringApplication.run(MusicaApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
//            this.initDados();
			this.inicializarService.inicializar();
//            this.inicializarShareKeysService.inicializar();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
