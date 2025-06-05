package pe.dinnra_web.sistema_gestion.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.dinnra_web.sistema_gestion.api.exceptions.RoomNotFoundException;
import pe.dinnra_web.sistema_gestion.api.mappers.RoomMapper;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.RoomRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.RoomDetailResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.RoomResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Room;
import pe.dinnra_web.sistema_gestion.api.repository.RoomRepository;
import pe.dinnra_web.sistema_gestion.api.service.RoomService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public RoomDetailResponse create(RoomRequest request) {
        Room room = roomMapper.toRoom(request);
        Room savedRoom = roomRepository.save(room);
        return roomMapper.toRoomDetailResponse(savedRoom);
    }

    @Override
    @Transactional(readOnly = true)
    public RoomDetailResponse findById(Long idRoom) {
        return roomRepository.findById(idRoom)
                .map(roomMapper::toRoomDetailResponse)
                .orElseThrow(() -> new RoomNotFoundException("Cuarto no encontrado con ID: " + idRoom));
    }

    @Override
    public Page<RoomResponse> findAll(Pageable pageable) {
        return roomRepository.findAll(pageable)
                .map(roomMapper::toRoomResponse);
    }

    @Override
    public List<RoomResponse> findAll(){
        return roomRepository.findAll()
                .stream().map(roomMapper::toRoomResponse)
                .toList();
    }

    @Override
    public void deleteById(Long idRoom) {
        if (!roomRepository.existsById(idRoom)){
            throw new RoomNotFoundException("Cuarto no encontrado con ID: " + idRoom);
        }
        roomRepository.deleteById(idRoom);
    }
}
