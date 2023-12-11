package br.ueg.musica.controller;


import br.ueg.musica.dto.GeneroDto;
import br.ueg.musica.mapper.GeneroMapper;
import br.ueg.musica.model.Genero;
import br.ueg.musica.service.GeneroService;
import br.ueg.prog.webi.api.controller.CrudController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "${app.api.base}/genero")
@PreAuthorize(value = "isAuthenticated()")
public class GeneroController extends CrudController<Genero, GeneroDto, Long, GeneroMapper, GeneroService> {

}
