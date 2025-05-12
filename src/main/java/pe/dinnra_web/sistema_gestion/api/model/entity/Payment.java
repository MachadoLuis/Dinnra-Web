package pe.dinnra_web.sistema_gestion.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "payments",
        indexes = {
                @Index(name = "idx_reservation", columnList = "id_reservation"),
                @Index(name = "idx_payment_method", columnList = "id_payment_method"),
                @Index(name = "idx_payment_price", columnList = "payment_price")
        }
)
public class Payment {

    @Id
    @Column(name = "id_payment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_payment_method")
    private PaymentMethod paymentMethod;

    @Column(name = "payment_price", nullable = false, precision = 8, scale = 2)
    private BigDecimal price;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

}
