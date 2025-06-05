package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
//@AllArgsConstructor
public class TokensResponse {

    private String refreshToken;

    private String token;

}
