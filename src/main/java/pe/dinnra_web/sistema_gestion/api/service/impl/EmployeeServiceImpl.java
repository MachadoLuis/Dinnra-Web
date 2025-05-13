package pe.dinnra_web.sistema_gestion.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.dinnra_web.sistema_gestion.api.exceptions.EmployeeNotFoundException;
import pe.dinnra_web.sistema_gestion.api.mappers.EmployeeMapper;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.EmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.EmployeeDetailResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.EmployeeResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Employee;
import pe.dinnra_web.sistema_gestion.api.repository.EmployeeRepository;
import pe.dinnra_web.sistema_gestion.api.service.EmployeeService;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDetailResponse create(EmployeeRequest request) {
        Employee employee = employeeMapper.toEmployee(request);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toEmployeeDetailResponse(savedEmployee);
    }

    @Override
    @Transactional(readOnly = true)
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
    public EmployeeDetailResponse update (Long idEmployee, EmployeeRequest request) {
        if (!employeeRepository.existsById(idEmployee)){
            throw new EmployeeNotFoundException("Empleado no encontrado con ID: " + idEmployee);
        }
        Employee employee = employeeRepository.findById(idEmployee)
                .orElseThrow(() -> new EmployeeNotFoundException("Empleado no encontrado con ID: " + idEmployee));
        employeeMapper.update(employee, request);
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
