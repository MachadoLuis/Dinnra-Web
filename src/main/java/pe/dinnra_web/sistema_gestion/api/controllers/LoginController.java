package pe.dinnra_web.sistema_gestion.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.LoginRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.LoginResponse;
import pe.dinnra_web.sistema_gestion.api.service.impl.LoginServiceImpl;

@Tag(name = "Logins", description = "Gestion de Inicio de Sesion con JavaWebToken")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginServiceImpl loginService;

    @Operation(summary = "Validar inicio de sesion")
    @PostMapping
    private ResponseEntity<LoginResponse> login (@Valid @RequestBody LoginRequest request){
        LoginResponse loginResponse = loginService.authenticate(request);
        return ResponseEntity.ok(loginResponse);
    }

}
