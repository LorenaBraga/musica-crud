package br.ueg.admin.repository;

import br.ueg.admin.model.ModuloModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuloRepository extends JpaRepository<ModuloModel, Long> {
}
