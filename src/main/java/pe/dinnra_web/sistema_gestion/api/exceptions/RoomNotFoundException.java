package pe.dinnra_web.sistema_gestion.api.exceptions;

public class RoomNotFoundException extends RuntimeException {
  public RoomNotFoundException(String message) {
    super(message);
  }
}
