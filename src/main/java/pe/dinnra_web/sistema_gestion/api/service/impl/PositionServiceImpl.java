package pe.dinnra_web.sistema_gestion.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.dinnra_web.sistema_gestion.api.exceptions.EmployeeNotFoundException;
import pe.dinnra_web.sistema_gestion.api.exceptions.PositionNotFoundException;
import pe.dinnra_web.sistema_gestion.api.exceptions.RoomNotFoundException;
import pe.dinnra_web.sistema_gestion.api.mappers.PositionMapper;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.PositionRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.PositionResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Position;
import pe.dinnra_web.sistema_gestion.api.repository.PositionRepository;
import pe.dinnra_web.sistema_gestion.api.service.PositionService;

import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public List<PositionResponse> findAll( ) {
        return positionRepository.findAll()
                .stream()
                .map(positionMapper::toPositionResponse).toList();
    }

    @Override
    public List<PositionResponse> findAllEmployedPositions() {
        return positionRepository.findByEmployeeIsTrue()
                .stream().map(positionMapper::toPositionResponse)
                .toList();
    }

    @Override
    public PositionResponse update(Long idPosition, PositionRequest positionRequest) {
        if (!positionRepository.existsById(idPosition)){
            throw new EmployeeNotFoundException("Posicion no encontrado con ID: " + idPosition);
        }
        Position position = positionRepository.findByIdPosition(idPosition);
        positionMapper.update(position, positionRequest);

        return positionMapper.toPositionResponse(position);
    }

    @Override
    public void deleteById(Long idPosition) {
        if (!positionRepository.existsById(idPosition)){
            throw new RoomNotFoundException("Posicion no encontrada con ID: " + idPosition);
        }
        positionRepository.deleteById(idPosition);
    }

}
