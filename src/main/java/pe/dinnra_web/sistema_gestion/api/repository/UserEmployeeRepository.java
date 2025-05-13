package pe.dinnra_web.sistema_gestion.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.dinnra_web.sistema_gestion.api.model.entity.UserEmployee;

import java.util.Optional;

public interface UserEmployeeRepository extends JpaRepository<UserEmployee, Long> {

    UserEmployee findByUsername (String username);

}
