package pe.dinnra_web.sistema_gestion.api.service;

import pe.dinnra_web.sistema_gestion.api.model.dto.request.PaymentMethodRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.PaymentMethodResponse;

public interface PaymentMethodService {
    PaymentMethodResponse create (PaymentMethodRequest paymentMethodRequest);

    PaymentMethodResponse findById(Long idPaymentMethod);
}
