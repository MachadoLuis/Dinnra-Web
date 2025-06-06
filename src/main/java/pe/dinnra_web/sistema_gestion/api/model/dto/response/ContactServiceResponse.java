package pe.dinnra_web.sistema_gestion.api.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import pe.dinnra_web.sistema_gestion.api.model.enums.ContactServiceType;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContactServiceResponse {
    private Long idContactService;
    private String clientFullName;
    private ContactServiceType contactServiceType;
    private String reclaim;
    private LocalDateTime createdAt;
}
