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
import pe.dinnra_web.sistema_gestion.api.model.dto.request.UserClientRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.UserClientResponse;
import pe.dinnra_web.sistema_gestion.api.service.impl.UserClientServiceImpl;

@Tag(name = "UserClient", description = "API para la gestion de usuarios de clientes")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-client")
public class UserClientController {

    private final UserClientServiceImpl userClientService;

    @Operation(summary = "Creacion usuario de cliente")
    @PostMapping
    private ResponseEntity<UserClientResponse> create (
            @Valid @RequestBody UserClientRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userClientService.create(request));
    }

    @Operation(summary = "Obtener un usuario por su ID")
    @GetMapping("/{idUserClient}")
    private ResponseEntity<UserClientResponse> findById (@PathVariable Long idUserClient){
        return ResponseEntity.ok(userClientService.findById(idUserClient));
    }

    @Operation(summary = "Obtener todos los usuarios de forma paginada")
    @GetMapping
    private ResponseEntity<Page<UserClientResponse>> findAll (Pageable pageable){
        return ResponseEntity.ok(userClientService.findAll(pageable));
    }

    @Operation(summary = "Eliminar un usuario por su ID")
    @DeleteMapping("/{idUserClient}")
    private ResponseEntity<Void> deleteById (@PathVariable Long idUserClient){
        userClientService.deleteById(idUserClient);
        return ResponseEntity.noContent().build();
    }

}
