package pe.dinnra_web.sistema_gestion.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "payment_methods",
        uniqueConstraints = {
                @UniqueConstraint(name = "UQ_payment_method_name", columnNames = "payment_method_name")
        }
)
public class PaymentMethod {

    @Id
    @Column(name = "id_payment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    @Column(name = "payment_method_name")
    private String name;

    @Column(name = "payment_method_description", columnDefinition = "TEXT")
    private String description;

}
