package pe.dinnra_web.sistema_gestion.api.exceptions;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(String message) {
        super(message);
    }
}
