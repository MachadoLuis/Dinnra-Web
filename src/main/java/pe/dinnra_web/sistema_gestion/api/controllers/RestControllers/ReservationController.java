package pe.dinnra_web.sistema_gestion.api.controllers.RestControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.ReservationRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ReservationResponse;
import pe.dinnra_web.sistema_gestion.api.service.impl.ReservationServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    @PostMapping
    private ResponseEntity<ReservationResponse> createReservation (@RequestBody ReservationRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reservationService.create(request));
    }

    @GetMapping("/{idReservation}")
    private ResponseEntity<ReservationResponse> findById(@PathVariable Long idReservation){
        return ResponseEntity.ok(reservationService.findById(idReservation));
    }

    @GetMapping
    private ResponseEntity<Page<ReservationResponse>> findAll (Pageable pageable){
        return ResponseEntity.ok(reservationService.findByAll(pageable));
    }

    @DeleteMapping("/{idReservation}")
    private ResponseEntity<Void> deleteById(@PathVariable Long idReservation){
        reservationService.deleteById(idReservation);
        return ResponseEntity.noContent().build();
    }

}
