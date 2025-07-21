package pe.dinnra_web.sistema_gestion.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.ReservationRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ReservationResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Reservation;
import pe.dinnra_web.sistema_gestion.api.service.impl.ClientServiceImpl;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "room", source = "room")
    @Mapping(target = "client", source = "client")
    ReservationResponse toResponse (Reservation reservation);

    @Mapping(target = "client.idClient", source = "idClient")
    @Mapping(target = "room.idRoom", source = "idRoom")
    Reservation toEntity (ReservationRequest request);

}
