package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginEmployeeResponse {

    private Long idUserEmployee;

    private String positionName;

    private String names;

    private String surnames;

    private String username;

    private Boolean activeEmployee;

    private Boolean activeAccount;

}
