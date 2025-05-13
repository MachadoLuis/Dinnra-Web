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
import pe.dinnra_web.sistema_gestion.api.model.dto.request.ClientRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ClientDetailResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ClientResponse;
import pe.dinnra_web.sistema_gestion.api.service.impl.ClientServiceImpl;

@Tag(name = "Clients", description = "API para la gestion de Clientes")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientServiceImpl clientService;

    @Operation(summary = "Creacion de cliente")
    @PostMapping
    private ResponseEntity<ClientDetailResponse> create (
            @Valid @RequestBody ClientRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientService.create(request));
    }

    @Operation(summary = "Obtener un cliente por su ID")
    @GetMapping("/{idClient}")
    private ResponseEntity<ClientDetailResponse> findById (@PathVariable Long idClient){
        return ResponseEntity.ok(clientService.findById(idClient));
    }

    @Operation(summary = "Obtener todos los clientes de forma paginada")
    @GetMapping
    private ResponseEntity<Page<ClientResponse>> findAll (Pageable pageable){
        return ResponseEntity.ok(clientService.findAll(pageable));
    }

    @Operation(summary = "Actualizar un cliente")
    @PutMapping("/{idClient}")
    private ResponseEntity<ClientDetailResponse> updateById (
            @PathVariable Long idClient, @Valid @RequestBody ClientRequest request){
        return ResponseEntity.ok(clientService.updateById(idClient,request));
    }

    @Operation(summary = "Eliminar un cliente por su ID")
    @DeleteMapping("/{idClient}")
    private ResponseEntity<Void> deleteById (@PathVariable Long idClient){
        clientService.deleteById(idClient);
        return ResponseEntity.noContent().build();
    }

}
