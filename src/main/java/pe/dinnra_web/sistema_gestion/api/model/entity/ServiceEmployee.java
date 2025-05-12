package pe.dinnra_web.sistema_gestion.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.dinnra_web.sistema_gestion.api.model.enums.EmployeeStatus;

@Getter
@Setter
@Entity
@Table(
        name = "service_employees",
        indexes = {
                @Index(name = "idx_employee", columnList = "id_employee"),
                @Index(name = "idx_employee_service_status", columnList = "employee_service_status")
        }
)
public class ServiceEmployee {

    @Id
    @Column(name = "id_service_employee")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServiceEmployee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

}
