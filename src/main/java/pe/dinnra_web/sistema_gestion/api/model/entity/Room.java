package pe.dinnra_web.sistema_gestion.api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.dinnra_web.sistema_gestion.api.model.enums.RoomStatus;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(
        name = "rooms",
        indexes = {
                @Index(name = "idx_room_name", columnList = "room_name"),
                @Index(name = "idx_room_capacity", columnList = "room_capacity"),
                @Index(name = "idx_room_price_per_night", columnList = "room_price_per_night"),
                @Index(name = "idx_room_status", columnList = "room_status")
        }
)
public class Room {

    @Id
    @Column(name = "id_room")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoom;

    @Column(name = "room_name")
    private String name;

    @Column(name = "room_amenities", columnDefinition = "TEXT")
    private String amenities;

    @Column(name = "room_description", columnDefinition = "TEXT" )
    private String description;

    @Column(name  = "room_capacity")
    private Integer capacity;

    @Column(name = "room_price_per_night", nullable = false, precision = 8, scale = 2)
    private BigDecimal pricePerNight;

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus = RoomStatus.DISPONIBLE;

    @Column(name = "room_img", columnDefinition = "TEXT")
    private String roomImg;

}
