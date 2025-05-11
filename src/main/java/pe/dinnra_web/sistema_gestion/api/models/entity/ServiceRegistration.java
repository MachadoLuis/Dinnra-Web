package pe.dinnra_web.sistema_gestion.api.models.entity;

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
        name = "service_registrations",
        indexes = {
                @Index(name = "idx_reservation", columnList = ("id_reservation")),
                @Index(name = "idx_service", columnList = ("id_service"))
        }
)
@EntityListeners(AuditingEntityListener.class)
public class ServiceRegistration {

    @Id
    @Column(name = "id_service_registration")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServiceRegistration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service")
    private Service service;

    @CreatedDate
    @Column(name = "service_registration_employee_assigned_at", updatable = false)
    private LocalDateTime assignedAt;
}
