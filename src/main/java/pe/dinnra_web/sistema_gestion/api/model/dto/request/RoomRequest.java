package pe.dinnra_web.sistema_gestion.api.model.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pe.dinnra_web.sistema_gestion.api.model.enums.RoomStatus;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class RoomRequest {

    @NotBlank(message = "El nombre del cuarto es requerido")
    private String name;

    @NotBlank(message = "Las amenidades del cuarto son necesarias")
    private String amenities;

    @NotBlank(message = "La descripcion del cuarto es necesaria")
    private String description;

    @NotNull(message = "La cantidad de personas maxima del cuarto es necesaria")
    private Integer capacity;

    @NotNull(message = "El precio por noche del cuarto es necesario")
    private BigDecimal pricePerNight;

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

}
