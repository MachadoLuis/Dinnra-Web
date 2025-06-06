package pe.dinnra_web.sistema_gestion.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.ContactServiceRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ContactServiceResponse;


public interface ContactServiceService {

    ContactServiceResponse create(ContactServiceRequest contactServiceRequest);

    ContactServiceResponse findById(Long idContactService);

    Page<ContactServiceResponse> findAll(Pageable pageable);
}
