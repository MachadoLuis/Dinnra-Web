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
import pe.dinnra_web.sistema_gestion.api.model.dto.request.RoomRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.RoomDetailResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.RoomResponse;
import pe.dinnra_web.sistema_gestion.api.service.impl.RoomServiceImpl;

@Tag(name = "Rooms", description = "API para la gestion de  Cuartos")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomServiceImpl roomService;

    @Operation(summary = "Creacion de Cuarto")
    @PostMapping
    private ResponseEntity<RoomDetailResponse> create (
            @Valid @RequestBody RoomRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(roomService.create(request));
    }

    @Operation(summary = "Obtener un cuarto por su ID")
    @GetMapping("/{idRoom}")
    private ResponseEntity<RoomDetailResponse> findById (@PathVariable Long idRoom){
        return ResponseEntity.ok(roomService.findById(idRoom));
    }

    @Operation(summary = "Obtener todos los cuartos de forma paginada")
    @GetMapping
    private ResponseEntity<Page<RoomResponse>> findAll (Pageable pageable){
        return ResponseEntity.ok(roomService.findAll(pageable));
    }

    @Operation(summary = "Eliminar un cuarto por su ID")
    @DeleteMapping("/{idRoom}")
    private ResponseEntity<Void> deleteById (@PathVariable Long idRoom){
        roomService.deleteById(idRoom);
        return ResponseEntity.noContent().build();
    }

}
