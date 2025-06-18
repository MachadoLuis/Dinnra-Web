package pe.dinnra_web.sistema_gestion.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.PositionRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.PositionResponse;

import java.util.List;

public interface PositionService {

    PositionResponse create (PositionRequest request);

    PositionResponse findById (Long idPosition);

    Page<PositionResponse> findAll (Pageable pageable);

    List<PositionResponse> findAll ();

    List<PositionResponse> findAllEmployedPositions ();

    PositionResponse update (Long idPosition, PositionRequest positionRequest);

    void deleteById(Long idPosition);

}
