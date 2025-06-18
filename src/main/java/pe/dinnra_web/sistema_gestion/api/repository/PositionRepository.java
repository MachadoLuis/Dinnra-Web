package pe.dinnra_web.sistema_gestion.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.dinnra_web.sistema_gestion.api.model.entity.Position;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {

    List<Position> findByEmployeeIsTrue ();

    Position findByIdPosition(Long idPosition);

}
