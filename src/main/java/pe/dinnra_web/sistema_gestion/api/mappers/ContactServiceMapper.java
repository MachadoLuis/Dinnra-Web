package pe.dinnra_web.sistema_gestion.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.ContactServiceRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ContactServiceResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.ContactService;

@Mapper(componentModel = "spring")
public interface ContactServiceMapper {
    @Mapping(target = "idContactService",ignore = true)
    @Mapping(target = "createAt",ignore = true)
    @Mapping(target = "client",source = "idClient")
    ContactService toContactService (ContactServiceRequest request);


    @Mapping(target = "clientFullName",source = "client")
    ContactServiceResponse toContactServiceResponse(ContactService contactService);

}
