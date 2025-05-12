package pe.dinnra_web.sistema_gestion.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.UserEmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.UserEmployeeResponse;

public interface UserEmployeeService {

    UserEmployeeResponse create (UserEmployeeRequest request);

    UserEmployeeResponse findById (Long idUserEmployee);

    Page<UserEmployeeResponse> findAll (Pageable pageable);

    void deleteById (Long idUserEmployee);

}
