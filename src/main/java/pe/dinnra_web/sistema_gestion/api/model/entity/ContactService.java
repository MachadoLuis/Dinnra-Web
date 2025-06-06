package pe.dinnra_web.sistema_gestion.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.dinnra_web.sistema_gestion.api.model.enums.ContactServiceType;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "contact_services",
        indexes = {
                @Index(name = "idx_client", columnList = "id_client"),
                @Index(name = "idx_contact_service_type", columnList = "contact_service_type")
        }
)
@EntityListeners(AuditingEntityListener.class)
public class ContactService {

    @Id
    @Column(name = "id_contact_service")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContactService;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_service_type")
    private ContactServiceType contactServiceType;

    @Column(name = "contact_service_reclaim", columnDefinition = "TEXT")
    private String reclaim;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}
