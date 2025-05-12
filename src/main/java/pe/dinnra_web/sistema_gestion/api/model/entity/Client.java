package pe.dinnra_web.sistema_gestion.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "clients",
        uniqueConstraints = {
                @UniqueConstraint(name = "UQ_client_email", columnNames = "client_email"),
                @UniqueConstraint(name = "UQ_client_phone", columnNames = "client_phone")
        },
        indexes = {
                @Index(name = "idx_client_names_surnames", columnList = "client_names, client_surnames"),
                @Index(name = "idx_client_email", columnList = "client_email")
        }
)
public class Client {

        @Id
        @Column(name = "id_client")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idClient;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_position")
        private Position position;

        @Column(name = "client_names")
        private String names;

        @Column(name = "client_surnames")
        private String surnames;

        @Column(name = "client_email")
        private String email;

        @Column(name = "client_phone")
        private String phone;

}
