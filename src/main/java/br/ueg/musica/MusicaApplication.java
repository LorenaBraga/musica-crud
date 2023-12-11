package br.ueg.musica;

import adminmodule.service.InicializarService;
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
		"adminmodule.*",
		"br.ueg.prog.webi.api.*", "br.ueg.prog.webi.*"
})
@EnableJpaRepositories(basePackages = {
		"br.ueg.musica.*",
		"adminmodule.*"
})
@EntityScan(basePackageClasses = {
		Jsr310JpaConverters.class }, basePackages = { "br.ueg.musica.*", "adminmodule.*"})
public class MusicaApplication extends AppStartupRunner {

	public static void main(String[] args) {
		SpringApplication.run(MusicaApplication.class, args);
	}

}
