package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientDetailResponse {

    private Long idClient;

    private String names;

    private String surnames;

    private String email;

    private String phone;

}
