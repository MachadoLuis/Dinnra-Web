package pe.dinnra_web.sistema_gestion.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.UserClientRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.UserClientResponse;

public interface UserClientService {

    UserClientResponse create (UserClientRequest request);

    UserClientResponse findById (Long idUserClient);

    Page<UserClientResponse> findAll (Pageable pageable);

    void deleteById (Long idUserClient);
}
