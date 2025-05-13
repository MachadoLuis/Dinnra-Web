package pe.dinnra_web.sistema_gestion.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.dinnra_web.sistema_gestion.api.model.entity.UserClient;

import java.util.Optional;

public interface UserClientRepository extends JpaRepository<UserClient, Long> {

    UserClient findByUsername (String username);

}
