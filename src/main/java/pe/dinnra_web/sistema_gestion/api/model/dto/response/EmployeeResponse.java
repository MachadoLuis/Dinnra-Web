package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeResponse {

    private Long idEmployee;

    private String positionName;

    private String usernames;

    private String email;

    private Boolean active;

}
