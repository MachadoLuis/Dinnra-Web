package pe.dinnra_web.sistema_gestion.api.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public class PaymentMethodRequest {

    @NotBlank
    private String name;

    private String description;

    // Getters y setters
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
