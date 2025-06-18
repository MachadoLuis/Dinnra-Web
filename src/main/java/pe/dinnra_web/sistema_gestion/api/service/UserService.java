package pe.dinnra_web.sistema_gestion.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserClientRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserEmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserEmployeeWebRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.registrationWebResponse.UserResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.User;
import pe.dinnra_web.sistema_gestion.api.util.UserInfo;

public interface UserService {

    UserResponse createClient (UserClientRequest request);

    UserResponse createEmployee (UserEmployeeRequest request);

    UserResponse createEmployeeRegistration (UserEmployeeWebRequest request);

    UserResponse findById (Long idUser);

    Page<UserResponse> findAll (Pageable pageable);

    UserInfo userInfo (String username);

}
