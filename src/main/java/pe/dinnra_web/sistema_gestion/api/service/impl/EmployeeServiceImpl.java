package pe.dinnra_web.sistema_gestion.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.dinnra_web.sistema_gestion.api.exceptions.EmployeeNotFoundException;
import pe.dinnra_web.sistema_gestion.api.mappers.EmployeeMapper;
import pe.dinnra_web.sistema_gestion.api.mappers.UserMapper;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.EmployeePatchRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.EmployeeRegistrationRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.EmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserEmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserEmployeeWebRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.EmployeeDetailResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.EmployeeRegistrationResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.EmployeeResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.registrationWebResponse.UserResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Employee;
import pe.dinnra_web.sistema_gestion.api.model.entity.Position;
import pe.dinnra_web.sistema_gestion.api.model.entity.User;
import pe.dinnra_web.sistema_gestion.api.repository.EmployeeRepository;
import pe.dinnra_web.sistema_gestion.api.repository.PositionRepository;
import pe.dinnra_web.sistema_gestion.api.repository.UserRepository;
import pe.dinnra_web.sistema_gestion.api.service.EmployeeService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UserServiceImpl userService;

    @Autowired
    @Lazy /*inicia la clase y luego inyecta la dependencia*/
    private EmployeeServiceImpl employeeService;

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public EmployeeDetailResponse create(EmployeeRequest request) {
        Employee employee = employeeMapper.toEmployee(request);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toEmployeeDetailResponse(savedEmployee);
    }

    @Override
    public EmployeeRegistrationResponse createEmployeeRegistration(EmployeeRegistrationRequest employeeRegistrationRequest) {
        try{
            EmployeeDetailResponse employeeDetailResponse = employeeService.create(employeeRegistrationRequest.getEmployeeRequest());
            UserEmployeeWebRequest userEmployeeWebRequest = employeeRegistrationRequest.getUserEmployeeWebRequest();
            userEmployeeWebRequest.setIdEmployee(employeeDetailResponse.getIdEmployee());
            UserResponse userResponse = userService.createEmployeeRegistration(userEmployeeWebRequest);

            return EmployeeRegistrationResponse.builder()
                    .employeeDetailResponse(employeeDetailResponse)
                    .userResponse(userResponse)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error al momento de completar la transaction", e);
        }
    }

    @Override
    public EmployeeDetailResponse findById (Long idEmployee) {
        return employeeRepository.findById(idEmployee)
                .map(employeeMapper::toEmployeeDetailResponse)
                .orElseThrow(() -> new EmployeeNotFoundException("Empleado no encontrado con ID: " + idEmployee));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeResponse> findAll (Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(employeeMapper::toEmployeeResponse);
    }

    @Override
    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll()
                .stream().map(employeeMapper::toEmployeeResponse)
                .toList();
    }

    @Override
    public EmployeeDetailResponse update (Long idEmployee, EmployeePatchRequest request) {
        if (!employeeRepository.existsById(idEmployee)){
            throw new EmployeeNotFoundException("Empleado no encontrado con ID: " + idEmployee);
        }
        Position position = positionRepository.findByIdPosition(request.getIdPosition());
        Employee employee = employeeRepository.findById(idEmployee)
                .orElseThrow(() -> new EmployeeNotFoundException("Empleado no encontrado con ID: " + idEmployee));
        employeeMapper.update(employee, request);
        employee.setPosition(position);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toEmployeeDetailResponse(savedEmployee);
    }

    @Override
    public void deleteById (Long idEmployee) {
        if (!employeeRepository.existsById(idEmployee)){
            throw new EmployeeNotFoundException("Empleado no encontrado con ID: " + idEmployee);
        }
        employeeRepository.deleteById(idEmployee);
    }
}
