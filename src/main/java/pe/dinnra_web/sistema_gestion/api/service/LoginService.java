package pe.dinnra_web.sistema_gestion.api.service;

import pe.dinnra_web.sistema_gestion.api.model.dto.request.LoginRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.LoginResponse;

public interface LoginService {

    LoginResponse authenticate (LoginRequest request);

}
