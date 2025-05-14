package pe.dinnra_web.sistema_gestion.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.RoomRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.RoomDetailResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.RoomResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Room;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "idRoom", ignore = true)
    Room toRoom (RoomRequest request);

    @Mapping(target = "idRoom", source = "idRoom")
    RoomDetailResponse toRoomDetailResponse (Room entity);

    @Mapping(target = "idRoom", source = "idRoom")
    RoomResponse toRoomResponse (Room entity);

}
