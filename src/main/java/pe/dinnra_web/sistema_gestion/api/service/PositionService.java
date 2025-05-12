package pe.dinnra_web.sistema_gestion.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.PositionRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.PositionResponse;

public interface PositionService {

    PositionResponse create (PositionRequest request);

    PositionResponse findById(Long idPosition);

    Page<PositionResponse> findAll (Pageable pageable);

}
