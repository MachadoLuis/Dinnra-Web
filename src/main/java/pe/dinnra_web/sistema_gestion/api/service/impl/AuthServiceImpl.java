package pe.dinnra_web.sistema_gestion.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.dinnra_web.sistema_gestion.api.exceptions.EmployeeNotFoundException;
import pe.dinnra_web.sistema_gestion.api.exceptions.UserNotFoundException;
import pe.dinnra_web.sistema_gestion.api.mappers.EmployeeMapper;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.LoginResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.TokensResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Employee;
import pe.dinnra_web.sistema_gestion.api.model.entity.User;
import pe.dinnra_web.sistema_gestion.api.model.enums.UserType;
import pe.dinnra_web.sistema_gestion.api.repository.ClientRepository;
import pe.dinnra_web.sistema_gestion.api.repository.EmployeeRepository;
import pe.dinnra_web.sistema_gestion.api.repository.UserRepository;
import pe.dinnra_web.sistema_gestion.api.service.AuthService;
import pe.dinnra_web.sistema_gestion.api.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;



    @Override
    @Transactional
    public TokensResponse authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        String position;
        /*
        if (user == null){
            throw new UserNotFoundException("No se encontro usuario");
        }
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("Contraseña invalida, no se pudo crear el token");
        }*/

        if (user == null || !passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("Error al validar las credenciales");
        }

        if (UserType.EMPLOYEE == user.getUserType()) {
            Employee employee = employeeRepository.findByIdEmployee(user.getIdEmployee());
            if (employee == null) {
                throw new EmployeeNotFoundException("No se encontro empleado con ID" + user.getIdEmployee());
            }
            position = employee.getPosition().getName();
        }else {
            position = "Client";
        }

        String refreshToken = jwtUtil.generateRefreshToken(String.valueOf(user.getIdUser()), user.getUsername(), position);
        String token = jwtUtil.generateTokenFromRefreshToken(refreshToken);

        return TokensResponse.builder()
                .refreshToken(refreshToken)
                .token(token)
                .build();
    }

    @Override
    public TokensResponse obtainTokens(String refreshToken){
        String token = jwtUtil.generateTokenFromRefreshToken(refreshToken);

        if (jwtUtil.isTokenExpired(refreshToken)){
            throw new IllegalArgumentException("Error");
        }
            return TokensResponse.builder()
                    .refreshToken(refreshToken)
                    .token(token)
                    .build();
    }


    @Override
    public LoginResponse extractInfoTokens(String refreshToken, String token) {
        if (refreshToken == null || refreshToken.trim().isEmpty()) {
            throw new IllegalArgumentException("El token JWT no puede ser null o vacío.");
        }
        return new LoginResponse(refreshToken,token, jwtUtil.extractClaims(token));
    }


}
