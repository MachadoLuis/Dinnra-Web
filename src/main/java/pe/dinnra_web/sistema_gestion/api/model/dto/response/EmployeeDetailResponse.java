package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import pe.dinnra_web.sistema_gestion.api.model.enums.Gender;

@Getter
@Builder
public class EmployeeDetailResponse {

    private Long idEmployee;

    private Long idPosition;

    private String positionName;

    private String names;

    private String usernames;

    private Gender gender;

    private String email;

    private Boolean active;

}
