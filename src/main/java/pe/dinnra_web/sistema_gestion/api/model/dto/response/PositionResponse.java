package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PositionResponse {

    private Long idPosition;

    private Boolean employee;

    private String name;

    private String description;

}
