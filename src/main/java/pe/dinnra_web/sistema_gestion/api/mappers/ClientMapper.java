package pe.dinnra_web.sistema_gestion.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.ClientRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ClientDetailResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ClientResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "idClient", ignore = true)
    @Mapping(target = "position.idPosition", source = "idPosition")
    Client toClient (ClientRequest request);

    ClientDetailResponse toClientDetailResponse (Client client);


    ClientResponse toClientResponse (Client client);

    @Mapping(target = "idClient", ignore = true)
    @Mapping(target = "position.idPosition", source = "idPosition")
    void update (@MappingTarget Client entity, ClientRequest request);

}
