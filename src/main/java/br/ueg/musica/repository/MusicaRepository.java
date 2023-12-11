package br.ueg.musica.repository;

import br.ueg.musica.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MusicaRepository extends JpaRepository<Musica, Long>, JpaSpecificationExecutor<Long> {

    @Query(" SELECT musica from Musica musica" +
            " JOIN musica.genero genero" +
            " WHERE musica.favorito = true")
        Optional<List<Musica>> listarFavoritas();
}

