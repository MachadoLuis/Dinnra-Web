package pe.dinnra_web.sistema_gestion.api.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pe.dinnra_web.sistema_gestion.api.models.enums.ServiceRegistrationLogStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "service_registration_logs",
        indexes = {
                @Index(name = "idx_service_registration", columnList = "id_service_registration"),
                @Index(name = "idx_service_registration_log_status", columnList = "service_registration_log_status")
        }
)
@EntityListeners(AuditingEntityListener.class)
public class ServiceRegistrationLog {

        @Id
        @Column(name = "id_service_registration_log")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idServiceRegistrationLog;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_service_registration")
        private ServiceRegistration serviceRegistration;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_service_employee")
        private ServiceEmployee serviceEmployee;

        @Enumerated(EnumType.STRING)
        private ServiceRegistrationLogStatus serviceRegistrationLogStatus;

        @CreatedDate
        @Column(name = "service_registration_log_status_update")
        private LocalDateTime updatedStatusAt;

}
