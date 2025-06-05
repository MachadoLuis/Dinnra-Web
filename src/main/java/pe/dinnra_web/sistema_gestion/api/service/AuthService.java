package pe.dinnra_web.sistema_gestion.api.service;

import pe.dinnra_web.sistema_gestion.api.model.dto.response.LoginResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.TokensResponse;

public interface AuthService {

    TokensResponse authenticate (String username, String password);

    TokensResponse obtainTokens(String refreshToken);

    LoginResponse extractInfoTokens (String refreshToken, String token);

}
