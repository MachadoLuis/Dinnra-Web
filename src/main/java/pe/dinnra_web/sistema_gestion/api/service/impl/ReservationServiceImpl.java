package pe.dinnra_web.sistema_gestion.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.dinnra_web.sistema_gestion.api.exceptions.ReservationNotFoundException;
import pe.dinnra_web.sistema_gestion.api.mappers.ReservationMapper;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.ReservationRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ReservationResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Reservation;
import pe.dinnra_web.sistema_gestion.api.model.enums.ReservationStatus;
import pe.dinnra_web.sistema_gestion.api.repository.ReservationRepository;
import pe.dinnra_web.sistema_gestion.api.service.ReservationService;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public ReservationResponse create(ReservationRequest request) {
        Reservation reservation = reservationMapper.toEntity(request);
        reservation.setReservationStatus(ReservationStatus.ACTIVA);
        return reservationMapper.toResponse(reservationRepository.save(reservation));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReservationResponse> findByAll(Pageable pageable) {
        return reservationRepository.findAll(pageable)
                .map(reservationMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationResponse findById(Long idReservation) {
        return reservationRepository.findById(idReservation)
                .map(reservationMapper::toResponse)
                .orElseThrow(() -> new ReservationNotFoundException("Reserva no encontrada con ID: " + idReservation));
    }

    @Override
    public void deleteById(Long idReservation) {
        if (!reservationRepository.existsById(idReservation)){
            throw new ReservationNotFoundException("Reserva no encontrada con ID: " + idReservation);
        }
        reservationRepository.deleteById(idReservation);
    }
}
