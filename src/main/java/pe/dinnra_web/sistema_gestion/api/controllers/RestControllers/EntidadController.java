package pe.dinnra_web.sistema_gestion.api.controllers.RestControllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.EntidadRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.EntidadResponse;
import pe.dinnra_web.sistema_gestion.api.service.impl.EntidadServiceImpl;

@RestController
@RequestMapping("/api/v1/entidad")
@RequiredArgsConstructor
public class EntidadController {

    private final EntidadServiceImpl entidadService;

    @PostMapping
    public ResponseEntity<EntidadResponse> create (@Valid @RequestBody EntidadRequest entidadRequest){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entidadService.create(entidadRequest));
    }

    @GetMapping("/{idEntidad}")
    public ResponseEntity<EntidadResponse> findById(@PathVariable Long idEntidad){

        return ResponseEntity.ok(entidadService.findById(idEntidad));

    }
}
