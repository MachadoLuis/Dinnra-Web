package pe.dinnra_web.sistema_gestion.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserClientRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserEmployeeRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.registrationWebRequest.UserEmployeeWebRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.registrationWebResponse.UserResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.User;
import pe.dinnra_web.sistema_gestion.api.util.UserInfo;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "idUser", ignore = true)
    //Expression asigna un valor en la implementacion del mapper
    @Mapping(target = "idEmployee", expression = "java(null)")
    @Mapping(target = "userType", source = "userType")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "createdAt", ignore = true)
    User clientToUser(UserClientRequest request);

    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "idClient", expression = "java(null)")
    @Mapping(target = "username",ignore = true)
    @Mapping(target = "userType", source = "userType")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "createdAt", ignore = true)
    User employeeToUser(UserEmployeeRequest request);

    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "idClient", expression = "java(null)")
    @Mapping(target = "username",ignore = true)
    @Mapping(target = "userType", source = "userType")
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User employeeWebToUser(UserEmployeeWebRequest request);

    @Mapping(target = "idUser", source = "idUser")
    UserResponse toUserResponse (User user);

    @Mapping(target = "idUser", source = "idUser")
    UserInfo toUserInfo (User user);

}
