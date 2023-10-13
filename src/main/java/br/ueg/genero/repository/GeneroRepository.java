package br.ueg.genero.repository;

import br.ueg.genero.model.GeneroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<GeneroModel, Long> {
}
