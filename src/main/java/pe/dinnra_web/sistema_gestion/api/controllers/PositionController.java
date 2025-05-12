package pe.dinnra_web.sistema_gestion.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.PositionRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.PositionResponse;
import pe.dinnra_web.sistema_gestion.api.service.impl.PositionServiceImpl;

@Tag(name = "Positions", description = "API para gestion de posiciones")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/position")

public class PositionController {

    private final PositionServiceImpl positionService;

    @Operation(summary = "Creacion de posicion")
    @PostMapping
    private ResponseEntity<PositionResponse> create (
            @Valid @RequestBody PositionRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(positionService.create(request));
    };

    @Operation(summary = "Obtener posicion por su ID")
    @GetMapping("/{idPosition}")
    private ResponseEntity<PositionResponse> findById(
            @PathVariable Long idPosition
    ){
       return ResponseEntity.ok(positionService.findById(idPosition));
    }

    @Operation(summary = "Obtener todas las posiciones de forma paginada")
    @GetMapping
    private ResponseEntity<Page<PositionResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(positionService.findAll(pageable));
    }

}
