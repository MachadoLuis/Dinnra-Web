package pe.dinnra_web.sistema_gestion.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.dinnra_web.sistema_gestion.api.model.enums.Gender;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(
        name = "employees",
        uniqueConstraints = {
                @UniqueConstraint(name = "UQ_employee_email", columnNames = "employee_email"),
                @UniqueConstraint(name = "UQ_employee_phone", columnNames = "employee_phone")
        },
        indexes = {
                @Index(name = "idx_employee_names_surnames", columnList = "employee_name, employee_surnames"),
                @Index(name = "idx_employee_email", columnList = "employee_email")
        }

)
public class Employee {

    @Id
    @Column(name = "id_employee")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_position")
    private Position position;

    @Column(name = "employee_names")
    private String names;

    @Column(name = "employee_surnames")
    private String surnames;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_gender")
    private Gender gender;

    @Column(name = "employee_birth_date")
    private LocalDate birthDate;

    @Column(name = "employee_email")
    private String email;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

}
