package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class EmployeeResponse {

    private Long idEmployee;

    private String positionName;

    private String surnames;

    private LocalDate birthDate;

    private String email;

    private Boolean active;

}
