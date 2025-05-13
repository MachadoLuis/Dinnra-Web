package pe.dinnra_web.sistema_gestion.api.exceptions;

public class UserClientNotFoundException extends RuntimeException {
    public UserClientNotFoundException(String message) {
        super(message);
    }
}
