package br.ueg.musica;

import br.ueg.genero.model.GeneroModel;
import br.ueg.genero.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"br.ueg.musica.*",
		"br.ueg.genero.*",
		"br.ueg.admin.*"
})
@EnableJpaRepositories(basePackages = {
		"br.ueg.musica.*",
		"br.ueg.genero.*",
		"br.ueg.admin.*",
})
@EntityScan(basePackageClasses = {
		Jsr310JpaConverters.class }, basePackages = {"br.ueg.*", "br.ueg.musica.*"})
public class MusicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicaApplication.class, args);
	}

}
