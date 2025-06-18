package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import pe.dinnra_web.sistema_gestion.api.model.entity.Position;
import pe.dinnra_web.sistema_gestion.api.model.enums.Gender;

import java.time.LocalDate;

@Getter
@Builder
public class EmployeeDetailResponse {

    private Long idEmployee;

    private Position position;

    private String names;

    private String surnames;

    private Gender gender;

    private LocalDate birthDate;

    private String email;

    private Boolean active;

}
