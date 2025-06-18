package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.registrationWebResponse.UserResponse;

@Getter
@Builder
public class EmployeeRegistrationResponse {

    EmployeeDetailResponse employeeDetailResponse;

    UserResponse userResponse;

}
