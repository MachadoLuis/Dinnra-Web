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
import pe.dinnra_web.sistema_gestion.api.model.dto.request.EmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.EmployeeDetailResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.EmployeeResponse;
import pe.dinnra_web.sistema_gestion.api.service.impl.EmployeeServiceImpl;

@Tag(name = "employees", description = "API para gestion de empleados")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @Operation(summary = "Creacion de empleado")
    @PostMapping
    private ResponseEntity<EmployeeDetailResponse> create (
            @Valid @RequestBody EmployeeRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.create(request));
    };

    @Operation(summary = "Obtener un empleado por su ID")
    @GetMapping("/{idEmployee}")
    private ResponseEntity<EmployeeDetailResponse> findById(@PathVariable Long idEmployee){
        return ResponseEntity.ok(employeeService.findById(idEmployee));
    }

    @Operation(summary = "Obtener todos los empleados de forma paginada")
    @GetMapping
    private ResponseEntity<Page<EmployeeResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(employeeService.findAll(pageable));
    }

    @Operation(summary = "Actualizar un empleado")
    @PutMapping("/{idEmployee}")
    private ResponseEntity<EmployeeDetailResponse> update(Long idEmployee, EmployeeRequest request){
        return ResponseEntity.ok(employeeService.update(idEmployee,request));
    }

    @Operation(summary = "Eliminar un empleado por su ID")
    @DeleteMapping("/{idEmployee}")
    private ResponseEntity<Void> deleteById(Long idEmployee){
        employeeService.deleteById(idEmployee);
        return ResponseEntity.noContent().build();
    }

}
