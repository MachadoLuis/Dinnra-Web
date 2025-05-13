package pe.dinnra_web.sistema_gestion.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.dinnra_web.sistema_gestion.api.exceptions.UserEmployeeNotFoundException;
import pe.dinnra_web.sistema_gestion.api.mappers.UserEmployeeMapper;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.UserEmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.UserEmployeeResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.UserEmployee;
import pe.dinnra_web.sistema_gestion.api.repository.UserEmployeeRepository;
import pe.dinnra_web.sistema_gestion.api.service.UserEmployeeService;

@Service
@Transactional
@RequiredArgsConstructor
public class UserEmployeeServiceImpl implements UserEmployeeService {
    private final UserEmployeeRepository userEmployeeRepository;
    private final UserEmployeeMapper userEmployeeMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEmployeeResponse create(UserEmployeeRequest request) {
        UserEmployee userEmployee = userEmployeeMapper.toUserEmployee(request);
        userEmployee.setPassword(passwordEncoder.encode(request.getPassword()));
        UserEmployee savedUserEmployee = userEmployeeRepository.save(userEmployee);
        return userEmployeeMapper.toUserEmployeeResponse(savedUserEmployee);
    }

    @Override
    @Transactional(readOnly = true)
    public UserEmployeeResponse findById(Long idUserEmployee) {
        return userEmployeeRepository.findById(idUserEmployee)
                .map(userEmployeeMapper::toUserEmployeeResponse)
                .orElseThrow(() -> new UserEmployeeNotFoundException("Usuario de empleado no encontrado con ID: " + idUserEmployee));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserEmployeeResponse> findAll(Pageable pageable) {
        return userEmployeeRepository.findAll(pageable)
                .map(userEmployeeMapper::toUserEmployeeResponse);
    }

    @Override
    public void deleteById(Long idUserEmployee) {
        if (!userEmployeeRepository.existsById(idUserEmployee)){
            throw new UserEmployeeNotFoundException("Usuario de empleado no encontrado con ID: " + idUserEmployee);
        }
        userEmployeeRepository.deleteById(idUserEmployee);
    }
}
