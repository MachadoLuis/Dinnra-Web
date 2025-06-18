package pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pe.dinnra_web.sistema_gestion.api.model.enums.UserType;

@Getter
@Setter
@Builder
public class UserEmployeeWebRequest {

    private Long idEmployee;

    @NotNull(message = "El tipo de usuario es requerido")
    private UserType userType;

    @NotBlank(message = "La contraseña no puede estar vacia")
    private String password;

    @NotNull(message = "El estado de la cuenta es necesario")
    private Boolean active;

}
