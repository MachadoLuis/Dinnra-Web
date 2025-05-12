package pe.dinnra_web.sistema_gestion.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.PositionRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.PositionResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Position;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    PositionResponse toPositionResponse(Position entity);

    @Mapping(target = "idPosition", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    Position toPosition(PositionRequest request);

}
