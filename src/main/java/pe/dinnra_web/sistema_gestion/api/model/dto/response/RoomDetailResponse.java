package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import pe.dinnra_web.sistema_gestion.api.model.enums.RoomStatus;

import java.math.BigDecimal;

@Getter
@Builder
public class RoomDetailResponse {

    private Long idRoom;

    private String name;

    private String amenities;

    private String description;

    private Integer capacity;

    private BigDecimal pricePerNight;

    private RoomStatus roomStatus;

    private String roomImg;

}
