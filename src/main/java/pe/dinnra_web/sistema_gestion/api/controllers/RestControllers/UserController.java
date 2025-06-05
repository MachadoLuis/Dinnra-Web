package pe.dinnra_web.sistema_gestion.api.controllers.RestControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserClientRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserEmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.registrationWebResponse.UserResponse;
import pe.dinnra_web.sistema_gestion.api.service.impl.UserServiceImpl;

@Tag(name = "Users", description = "API para el manejo de usuarios")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/user-client")
    private ResponseEntity<UserResponse> createUserClient (@Valid @RequestBody UserClientRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createClient(request));
    }

    @PostMapping("/user-employee")
    private ResponseEntity<UserResponse> createUserEmployee (@Valid @RequestBody UserEmployeeRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createEmployee(request));
    }

    @GetMapping("/{idUser}")
    private ResponseEntity<UserResponse> findUserById(@PathVariable Long idUser){
        return ResponseEntity.ok(userService.findById(idUser));
    }

    @GetMapping
    private ResponseEntity<Page<UserResponse>> findAll (Pageable pageable){
        return ResponseEntity.ok(userService.findAll(pageable));
    }

}
