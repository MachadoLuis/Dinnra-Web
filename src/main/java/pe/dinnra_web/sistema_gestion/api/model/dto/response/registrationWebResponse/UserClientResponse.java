package pe.dinnra_web.sistema_gestion.api.model.dto.response.registrationWebResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pe.dinnra_web.sistema_gestion.api.model.enums.UserType;

@Getter
@Builder
public class UserClientResponse {
    private Long idUser;

    private UserType userType;

    private String idClient;

    private String username;

    private String password;

    private Boolean active;

}
