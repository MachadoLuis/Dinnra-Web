package pe.dinnra_web.sistema_gestion.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.RoomRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.RoomDetailResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.RoomResponse;

import java.util.List;

public interface RoomService {

    RoomDetailResponse create (RoomRequest request);

    RoomDetailResponse findById (Long idRoom);

    Page<RoomResponse> findAll (Pageable pageable);

    List<RoomResponse> findAll ();

    void deleteById (Long idRoom);


}
