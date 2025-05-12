package pe.dinnra_web.sistema_gestion.api.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pe.dinnra_web.sistema_gestion.api.model.enums.Gender;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class EmployeeRequest {

    @NotNull(message = "La posicion es requerida")
    private Long idPosition;

    @NotBlank(message = "Los nombres no pueden estar vacios")
    private String names;

    @NotBlank(message = "Los apellidos no pueden estar vacios")
    private String surnames;

    @NotBlank(message = "El genero es requerido")
    private Gender gender;

    @NotNull(message = "El año de nacimiento es requerido")
    private LocalDate birthDate;

    @NotBlank(message = "El Correo es requerido")
    @Email(message = "Debe ingresar un email valido")
    private String email;

    @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe tener 9 dígitos")
    private String phone;

    @NotNull(message = "El estado es requerido")
    private Boolean active;

}
