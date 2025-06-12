package pe.dinnra_web.sistema_gestion.api.model.dto.response;

public class PaymentMethodResponse {

    private Long idPaymentMethod;
    private String name;
    private String description;

    // Getters y setters
    public Long getIdPaymentMethod() {
        return idPaymentMethod;
    }
    public void setIdPaymentMethod(Long idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
