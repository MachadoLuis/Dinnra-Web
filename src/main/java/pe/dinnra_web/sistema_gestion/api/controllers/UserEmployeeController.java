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
import pe.dinnra_web.sistema_gestion.api.model.dto.request.UserEmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.UserEmployeeResponse;
import pe.dinnra_web.sistema_gestion.api.service.impl.UserEmployeeServiceImpl;

@Tag(name = "UserEmployees", description = "API para la gestion de usuarios de Empleados")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-employee")
public class UserEmployeeController {

    private final UserEmployeeServiceImpl userEmployeeService;

    @Operation(summary = "Creacion usuario de empleado")
    @PostMapping
    private ResponseEntity<UserEmployeeResponse> create (
            @Valid @RequestBody UserEmployeeRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userEmployeeService.create(request));
    }

    @Operation(summary = "Obtener un usuario por su ID")
    @GetMapping("/{idUserEmployee}")
    private ResponseEntity<UserEmployeeResponse> findById (@PathVariable Long idUserEmployee){
        return ResponseEntity.ok(userEmployeeService.findById(idUserEmployee));
    }

    @Operation(summary = "Obtener todos los usuarios de forma paginada")
    @GetMapping
    private ResponseEntity<Page<UserEmployeeResponse>> findAll (Pageable pageable){
        return ResponseEntity.ok(userEmployeeService.findAll(pageable));
    }

    @Operation(description = "Eliminar un usuario por su ID")
    @DeleteMapping("/{idUserEmployee}")
    private ResponseEntity<Void> deleteById (@PathVariable Long idUserEmployee){
        userEmployeeService.deleteById(idUserEmployee);
        return ResponseEntity.noContent().build();
    }

}
