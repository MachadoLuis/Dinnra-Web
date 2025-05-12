package pe.dinnra_web.sistema_gestion.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "contact_service_registrations",
        indexes = {
                @Index(name = "idx_employee", columnList = "id_employee"),
                @Index(name = "idx_contact_service", columnList = "id_contact_service")
        }
)
@EntityListeners(AuditingEntityListener.class)
public class ContactServiceRegistration {

    @Id
    @Column(name = "id_contact_service_registration")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContactServiceRegistration;

    @OneToOne(fetch = FetchType.LAZY)
    private ContactService contactService;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @CreatedDate
    @Column(name = "opened_at")
    private LocalDateTime openedAt;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

}
