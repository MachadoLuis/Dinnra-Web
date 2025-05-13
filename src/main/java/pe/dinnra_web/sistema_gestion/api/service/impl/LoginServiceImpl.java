package pe.dinnra_web.sistema_gestion.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.dinnra_web.sistema_gestion.api.exceptions.ClientNotFoundException;
import pe.dinnra_web.sistema_gestion.api.exceptions.UserClientNotFoundException;
import pe.dinnra_web.sistema_gestion.api.exceptions.UserEmployeeNotFoundException;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.LoginRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.LoginResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.UserClient;
import pe.dinnra_web.sistema_gestion.api.model.entity.UserEmployee;
import pe.dinnra_web.sistema_gestion.api.repository.UserClientRepository;
import pe.dinnra_web.sistema_gestion.api.repository.UserEmployeeRepository;
import pe.dinnra_web.sistema_gestion.api.service.LoginService;
import pe.dinnra_web.sistema_gestion.api.util.JavaWebTokenUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserEmployeeRepository employeeRepository;
    private final UserClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    private final JavaWebTokenUtil jwtUtil;


    @Override
    @Transactional
    public LoginResponse authenticate(LoginRequest request) {

        UserEmployee userEmployee = employeeRepository.findByUsername(request.getUsername());

        /*assert userEmployee != null;*/
        if (userEmployee != null && passwordEncoder.matches(request.getPassword(), userEmployee.getPassword())){
            String position = userEmployee.getEmployee().getPosition().getName();
            String token = jwtUtil.generateToken(userEmployee.getUsername(), userEmployee.getEmployee().getPosition().getName());
            return new LoginResponse(request.getUsername(),request.getPassword(), position, token);
        }

        UserClient userClient = clientRepository.findByUsername(request.getUsername());

        if (userClient != null &&passwordEncoder.matches(request.getPassword(), userClient.getPassword())){
            String position = userClient.getClient().getPosition().getName();
            String token = jwtUtil.generateToken(request.getUsername(), position);
            return new LoginResponse(request.getUsername(), request.getPassword(), position, token);
        }
        throw new RuntimeException("CREDENCIALES INVALIDAS");
    }
}
