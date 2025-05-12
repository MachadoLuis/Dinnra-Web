package pe.dinnra_web.sistema_gestion.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PositionRequest {

    @NotNull(message = "Se debe especificar si la posicion sera un empleado o no")
    private Boolean employee;

    @NotBlank(message = "La posicion debe contar con un nombre")
    private String name;

    @NotBlank(message = "La descripcion de la posicion no puede estar vacia")
    private String description;

}
