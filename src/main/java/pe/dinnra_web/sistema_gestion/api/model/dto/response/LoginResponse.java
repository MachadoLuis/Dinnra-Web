package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {

    private String username;

    private String password;

    private String position;

    private String token;

}
