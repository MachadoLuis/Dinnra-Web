package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginClientResponse {

    private Long idUserClient;

    private String names;

    private String surnames;

    private String username;

    private Boolean activeAccount;

}
