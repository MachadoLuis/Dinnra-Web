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
        name = "news_subscriptions",
        uniqueConstraints = {
                @UniqueConstraint(name = "UQ_news_subscription_email", columnNames = "news_subscription_email")
        }
)
@EntityListeners(AuditingEntityListener.class)
public class NewsSubscription {

    @Id
    @Column(name = "id_news_subscription")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNewsSubscription;

    @Column(name = "news_subscription_email")
    private String email;

    @CreatedDate
    @Column(name = "subscribed_at")
    private LocalDateTime subscribedAt;

}
