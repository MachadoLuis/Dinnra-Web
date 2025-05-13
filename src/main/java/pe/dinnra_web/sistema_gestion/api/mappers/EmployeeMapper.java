package pe.dinnra_web.sistema_gestion.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.EmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.EmployeeDetailResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.EmployeeResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "idEmployee", ignore = true)
    @Mapping(target = "position.idPosition", source = "idPosition")
    Employee toEmployee (EmployeeRequest request);

    @Mapping(target = "positionName", source = "position.name")
    EmployeeDetailResponse toEmployeeDetailResponse (Employee employee);

    @Mapping(target = "positionName", source = "position.name")
    EmployeeResponse toEmployeeResponse(Employee employee);

    @Mapping(target = "idEmployee", ignore = true)
    @Mapping(target = "position.idPosition", source = "idPosition")
    void update(@MappingTarget Employee entity, EmployeeRequest request);

}
