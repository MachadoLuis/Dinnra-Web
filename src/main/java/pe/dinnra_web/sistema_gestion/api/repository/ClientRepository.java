package pe.dinnra_web.sistema_gestion.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.dinnra_web.sistema_gestion.api.model.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByIdClient(Long idClient);

}
