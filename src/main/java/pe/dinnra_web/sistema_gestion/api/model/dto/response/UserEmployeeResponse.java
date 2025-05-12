package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserEmployeeResponse {

    private Long idClient;

    private String username;

    private String password;

    private Boolean active;

}
