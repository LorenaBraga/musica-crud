package br.ueg.musica.repository;

import br.ueg.musica.model.MusicaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<MusicaModel, Long> {

}

