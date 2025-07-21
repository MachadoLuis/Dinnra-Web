package pe.dinnra_web.sistema_gestion.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.ClientRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ClientDetailResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ClientResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Client;

public interface ClientService {

    ClientDetailResponse create (ClientRequest request);

    Client findByIdEntity (Long idClient);
    ClientDetailResponse findById (Long idClient);

    Page<ClientResponse> findAll (Pageable pageable);

    ClientDetailResponse updateById (Long idClient, ClientRequest request);

    void deleteById (Long idClient);

}
