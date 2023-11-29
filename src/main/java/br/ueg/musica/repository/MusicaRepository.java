package br.ueg.musica.repository;

import br.ueg.musica.dto.MusicasFavoritasDto;
import br.ueg.musica.dto.MusicasFavoritasGeneroDto;
import br.ueg.musica.model.MusicaModel;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MusicaRepository extends JpaRepository<MusicaModel, Long> {

    @Query(" SELECT musica from MusicaModel musica" +
            " JOIN musica.genero genero" +
            " WHERE musica.favorito = true")
        Optional<List<MusicaModel>> listarFavoritas();
}

