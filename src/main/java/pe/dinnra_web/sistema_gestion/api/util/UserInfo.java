package pe.dinnra_web.sistema_gestion.api.util;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pe.dinnra_web.sistema_gestion.api.model.enums.UserType;

@Getter
@Builder
@AllArgsConstructor
public class UserInfo {

    private Long idUser;

    private UserType userType;

    private String username;

    private Boolean active;

}
