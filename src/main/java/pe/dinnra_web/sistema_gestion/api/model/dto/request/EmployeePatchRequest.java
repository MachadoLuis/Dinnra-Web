package pe.dinnra_web.sistema_gestion.api.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pe.dinnra_web.sistema_gestion.api.model.enums.Gender;

@Getter
@Setter
@Builder
public class EmployeePatchRequest {

    private Long idPosition;
    private String surnames;
    private Gender gender;
    private Boolean active;

}
