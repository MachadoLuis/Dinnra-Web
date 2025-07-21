package pe.dinnra_web.sistema_gestion.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import pe.dinnra_web.sistema_gestion.api.model.enums.ReservationStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "reservations",
        indexes = {
                @Index(name = "idx_client", columnList = "id_client"),
                @Index(name = "idx_id_room", columnList = "id_room")
        }
)
@EntityListeners(AuditingEntityListener.class)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long idReservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_room")
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_status")
    private ReservationStatus reservationStatus;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "reservation_check_in", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime checkIn;

    @Column(name = "reservation_check_out", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime checkOut;
    
}
