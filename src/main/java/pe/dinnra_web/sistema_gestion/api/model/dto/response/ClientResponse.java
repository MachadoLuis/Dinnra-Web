package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientResponse {

    private Long idClient;

    private String surnames;

    private String email;

}
