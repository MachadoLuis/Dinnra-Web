package pe.dinnra_web.sistema_gestion.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.dinnra_web.sistema_gestion.api.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByIdUser (Long idUser);
    User findByUsername (String username);

}
