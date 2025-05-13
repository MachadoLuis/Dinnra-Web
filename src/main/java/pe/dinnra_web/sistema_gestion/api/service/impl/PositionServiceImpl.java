package pe.dinnra_web.sistema_gestion.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.dinnra_web.sistema_gestion.api.exceptions.PositionNotFoundException;
import pe.dinnra_web.sistema_gestion.api.mappers.PositionMapper;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.PositionRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.PositionResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Position;
import pe.dinnra_web.sistema_gestion.api.repository.PositionRepository;
import pe.dinnra_web.sistema_gestion.api.service.PositionService;

@Service
@Transactional
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    @Override
    public PositionResponse create(PositionRequest request) {
        Position position =positionMapper.toPosition(request);
        Position savedPosition = positionRepository.save(position);
        return positionMapper.toPositionResponse(savedPosition);
    }

    @Override
    @Transactional(readOnly = true)
    public PositionResponse findById(Long idPosition) {
        return positionRepository.findById(idPosition)
                .map(positionMapper::toPositionResponse)
                .orElseThrow(() -> new PositionNotFoundException("Posicion no encontrada con ID: " + idPosition));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PositionResponse> findAll(Pageable pageable) {
        return positionRepository.findAll(pageable)
                .map(positionMapper::toPositionResponse);
    }
}
