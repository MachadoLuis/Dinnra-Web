package pe.dinnra_web.sistema_gestion.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.EmployeePatchRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.EmployeeRegistrationRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.EmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.EmployeeDetailResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.EmployeeRegistrationResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeDetailResponse create (EmployeeRequest request);

    EmployeeRegistrationResponse createEmployeeRegistration (EmployeeRegistrationRequest employeeRegistrationRequest);

    EmployeeDetailResponse findById (Long idEmployee);

    Page<EmployeeResponse> findAll (Pageable pageable);

    List<EmployeeResponse> findAll ();

    EmployeeDetailResponse update (Long idEmployee, EmployeePatchRequest request);

    void deleteById(Long idEmployee);


}
