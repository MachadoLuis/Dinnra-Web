package pe.dinnra_web.sistema_gestion.api.exceptions;

public class UserEmployeeNotFoundException extends RuntimeException {
    public UserEmployeeNotFoundException(String message) {
        super(message);
    }
}
