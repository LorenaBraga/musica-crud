package br.ueg.musica.repository;

import br.ueg.musica.model.GeneroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<GeneroModel, Long> {
}
