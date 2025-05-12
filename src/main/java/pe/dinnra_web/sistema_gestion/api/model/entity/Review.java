package pe.dinnra_web.sistema_gestion.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "reviews",
        indexes = {
                @Index(name = "idx_reservation", columnList = "id_reservation"),
                @Index(name = "idx_review_rating", columnList = "review_rating")
        }
)
//EN DTO REQUEST, CREAR UN CONSTRAINT CHECK
public class Review {

    @Id
    @Column(name = "id_review")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReview;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    @Column(name = "review_rating")
    private Integer rating;

    @Column(name = "review_comment", columnDefinition = "TEXT")
    private String comment;

}
