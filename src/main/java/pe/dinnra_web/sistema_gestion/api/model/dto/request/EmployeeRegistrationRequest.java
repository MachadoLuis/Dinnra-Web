package pe.dinnra_web.sistema_gestion.api.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserEmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserEmployeeWebRequest;

@Getter
@Setter
@Builder
public class EmployeeRegistrationRequest {

    EmployeeRequest employeeRequest;

    UserEmployeeWebRequest userEmployeeWebRequest;

}
