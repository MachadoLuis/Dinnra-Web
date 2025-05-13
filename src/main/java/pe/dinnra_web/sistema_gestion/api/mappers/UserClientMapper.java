package pe.dinnra_web.sistema_gestion.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.UserClientRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.UserClientResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.UserClient;

@Mapper(componentModel = "spring")
public interface UserClientMapper {

    @Mapping(target = "idUserClient", ignore = true)
    @Mapping(target = "client.idClient", source = "idClient")
    @Mapping(target = "username", source = "username")
    UserClient toUserClient (UserClientRequest request);

    @Mapping(target = "idClient", source = "client.idClient")
    @Mapping(target = "surnames", source = "client.surnames")
    UserClientResponse toUserClientResponse (UserClient userClient);

}
