package pe.dinnra_web.sistema_gestion.api.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserEmployeeRequest {

    @NotNull(message = "El id de empleado es requerido")
    private Long idEmployee;

    @NotBlank(message = "El campo es requerido")
    @Email(message = "Ingrese un email valido")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una letra y un número")
    private String password;

}
