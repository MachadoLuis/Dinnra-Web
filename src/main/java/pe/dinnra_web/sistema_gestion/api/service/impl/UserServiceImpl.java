package pe.dinnra_web.sistema_gestion.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.dinnra_web.sistema_gestion.api.exceptions.UserNotFoundException;
import pe.dinnra_web.sistema_gestion.api.mappers.UserMapper;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserClientRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserEmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserEmployeeWebRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.registrationWebResponse.UserResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.User;
import pe.dinnra_web.sistema_gestion.api.repository.EmployeeRepository;
import pe.dinnra_web.sistema_gestion.api.repository.UserRepository;
import pe.dinnra_web.sistema_gestion.api.service.UserService;
import pe.dinnra_web.sistema_gestion.api.util.UserInfo;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponse createClient(UserClientRequest request) {
        User user = userMapper.clientToUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponse createEmployee(UserEmployeeRequest request) {
        String email = employeeRepository.findByIdEmployee(request.getIdEmployee()).getEmail();
        User user = userMapper.employeeToUser(request);
        user.setUsername(email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponse createEmployeeRegistration(UserEmployeeWebRequest request) {
        String email = employeeRepository.findByIdEmployee(request.getIdEmployee()).getEmail();
        User user = userMapper.employeeWebToUser(request);
        user.setUsername(email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUSer = userRepository.save(user);
        return userMapper.toUserResponse(savedUSer);
    }


    @Override
    public UserResponse findById(Long idUser) {
        return userRepository.findById(idUser)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new UserNotFoundException("Usuario no ecnotrado con ID: " + idUser));
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toUserResponse);
    }

    @Override
    public UserInfo userInfo(String username) {
        User user = userRepository.findByUsername(username);
        /*
        if (user == null){
            throw new UserNotFoundException("Este correo no esta asociado a un usuario");
        }*/
        return userMapper.toUserInfo(user);
    }
}
