package pe.dinnra_web.sistema_gestion.api.model.dto.response.registrationWebResponse;

import lombok.Builder;
import lombok.Getter;
import pe.dinnra_web.sistema_gestion.api.model.enums.UserType;

@Getter
@Builder
public class UserResponse {

    private Long idUser;

    private UserType userType;

    private String username;

    private String password;

    private Boolean active;

}
