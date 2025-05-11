package pe.dinnra_web.sistema_gestion.api.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "services",
        uniqueConstraints = {
                @UniqueConstraint(name = "UQ_service_name", columnNames = "service_name")
        }
)
public class Service {

    @Id
    @Column(name = "id_service")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_service;

    @Column(name = "service_name")
    private String name;

    @Column(name = "service_description")
    private String description;

}
