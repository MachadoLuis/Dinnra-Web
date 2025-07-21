package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pe.dinnra_web.sistema_gestion.api.model.entity.Client;
import pe.dinnra_web.sistema_gestion.api.model.enums.ReservationStatus;
import pe.dinnra_web.sistema_gestion.api.model.enums.RoomStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ReservationResponse {

    private Long idReservation;

    private ClientResponse client;

    private RoomDetailResponse room;

    private ReservationStatus reservationStatus;

    private LocalDateTime createdAt;

}
