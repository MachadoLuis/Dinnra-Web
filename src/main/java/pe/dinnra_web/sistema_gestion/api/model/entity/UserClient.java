package pe.dinnra_web.sistema_gestion.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "user_clients",
        uniqueConstraints = {
                @UniqueConstraint(name = "UQ_user_client_username", columnNames = "user_client_username")
        },
        indexes = {
                @Index(name = "idx_user_client_username", columnList = "user_client_username")
        }
)
public class UserClient {

    @Id
    @Column(name = "id_user_client")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserClient;

    @OneToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @Column(name = "user_client_password")
    private String password;

}
