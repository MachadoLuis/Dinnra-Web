package pe.dinnra_web.sistema_gestion.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.ReservationRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ReservationResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Reservation;

import java.util.List;

public interface ReservationService {

    ReservationResponse create (ReservationRequest request);

    Page<ReservationResponse> findByAll (Pageable pageable);

    ReservationResponse findById(Long idReservation);

    void deleteById(Long idReservation);


}
