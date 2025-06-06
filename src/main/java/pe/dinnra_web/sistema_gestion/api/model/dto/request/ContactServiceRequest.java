package pe.dinnra_web.sistema_gestion.api.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import pe.dinnra_web.sistema_gestion.api.model.enums.ContactServiceType;

@Getter
@Setter
public class ContactServiceRequest {

    private Long idClient;
    private ContactServiceType contactServiceType;
    private String reclaim;

}
