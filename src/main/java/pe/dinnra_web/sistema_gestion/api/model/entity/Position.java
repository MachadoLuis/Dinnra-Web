package pe.dinnra_web.sistema_gestion.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name ="positions",
        uniqueConstraints = {
                @UniqueConstraint(name="UQ_position_name", columnNames = "position_name")
        },
        indexes = {
                @Index(name="idx_position_name", columnList = "position_name")
        }
)
//Solo para entidades que usen @CreatedDate
//@EntityListeners(EnableJpaAuditing.class)
public class Position {

    @Id
    @Column(name = "id_position")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPosition;

    @Column(name = "position_name")
    private String name;

    @Column(name = "position_description", columnDefinition = "TEXT")
    private String description;


}
