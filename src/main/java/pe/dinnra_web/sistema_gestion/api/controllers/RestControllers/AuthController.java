package pe.dinnra_web.sistema_gestion.api.controllers.RestControllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.LoginRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.LoginResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.TokensResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.User;
import pe.dinnra_web.sistema_gestion.api.repository.UserRepository;
import pe.dinnra_web.sistema_gestion.api.service.impl.AuthServiceImpl;
import pe.dinnra_web.sistema_gestion.api.util.JwtUtil;

import java.net.http.HttpRequest;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthServiceImpl authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    private ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        TokensResponse tokens= authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        return /*ResponseEntity.ok(authService.extractInfoToken(token))*/
                ResponseEntity.ok()
                        /*.header("Authorization", "Bearer " + token )*/
                        .body(authService.extractInfoTokens(tokens.getRefreshToken(), tokens.getToken()));
    }


    @PostMapping("/login-web")
    public ResponseEntity<LoginResponse> loginWeb (@Valid @ModelAttribute LoginRequest loginRequest){
        TokensResponse tokens= authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        return
                ResponseEntity.ok()
                        .body(authService.extractInfoTokens(tokens.getRefreshToken(), tokens.getToken()));
    }

    @PostMapping("/refresh-token")
    private ResponseEntity<TokensResponse> refreshToken (@RequestBody  Map<String, String> refreshToken){
        return ResponseEntity.ok()
                .body(authService.obtainTokens(refreshToken.get("refresh_token")));
    }


    /*
    @PostMapping("/refresh-token")
    private ResponseEntity<TokensResponse> refreshToken (HttpServletRequest httpRequest){
        String authHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String refreshToken = authHeader.substring(7);
        return ResponseEntity.ok()
                .body(authService.obtainTokens(refreshToken));
    }*/

    @GetMapping("/validate-auth")
    private ResponseEntity<?> validateAuthentication(Authentication authentication){

        if (authentication !=null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return ResponseEntity.ok().build();
    }


}
