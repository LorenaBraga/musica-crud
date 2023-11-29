package br.ueg.musica.controller;


import br.ueg.musica.dto.GeneroDto;
import br.ueg.musica.mapper.GeneroMapper;
import br.ueg.musica.model.GeneroModel;
import br.ueg.musica.service.GeneroService;
import br.ueg.prog.webi.api.controller.CrudController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${app.api.base}/genero")
public class GeneroController extends CrudController<GeneroModel, GeneroDto, Long, GeneroMapper, GeneroService> {


}
