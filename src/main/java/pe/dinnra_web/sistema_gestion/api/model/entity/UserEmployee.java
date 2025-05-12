package pe.dinnra_web.sistema_gestion.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "user_employees",
        uniqueConstraints = {
                @UniqueConstraint(name = "UQ_user_employee_username", columnNames = "user_employee_username")
        },
        indexes = {
                @Index(name = "idx_user_employee", columnList = "user_employee_username")
        }
)
public class UserEmployee {

    @Id
    @Column(name = "id_user_employee")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserEmployee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @Column(name = "user_employee_username")
    private String username;

    @Column(name = "user_employee_password")
    private String password;

}
