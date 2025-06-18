package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class EmployeeResponse {

    private Long idEmployee;

    private String surnames;

    private String gender;

    private String positionName;

    private String email;

    private Boolean active;

}
