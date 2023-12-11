package br.ueg.musica.repository;

import br.ueg.musica.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GeneroRepository extends JpaRepository<Genero, Long>, JpaSpecificationExecutor<Genero> {
}
