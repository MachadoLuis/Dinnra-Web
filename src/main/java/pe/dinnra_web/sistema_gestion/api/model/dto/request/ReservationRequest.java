package pe.dinnra_web.sistema_gestion.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pe.dinnra_web.sistema_gestion.api.model.enums.ReservationStatus;

import java.util.Date;

@Getter
@Setter
@Builder
public class ReservationRequest {

    @NotNull
    private Long idClient;

    @NotNull
    private Long idRoom;

    @NotBlank
    private Date checkIn;

    @NotBlank
    private Date checkOut;

}
