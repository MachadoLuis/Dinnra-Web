package pe.dinnra_web.sistema_gestion.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.dinnra_web.sistema_gestion.api.model.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
