package pe.dinnra_web.sistema_gestion.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.UserEmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.UserEmployeeResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.UserEmployee;

@Mapper(componentModel = "spring")
public interface UserEmployeeMapper {

    @Mapping(target = "idUserEmployee", ignore = true)
    @Mapping(target = "employee.idEmployee", source = "idEmployee")
    UserEmployee toUserEmployee (UserEmployeeRequest request);

    @Mapping(target = "idEmployee", source = "employee.idEmployee")
    @Mapping(target = "positionName", source = "employee.position.name")
    @Mapping(target = "surnames", source = "employee.surnames")
    UserEmployeeResponse toUserEmployeeResponse (UserEmployee userEmployee);

}
