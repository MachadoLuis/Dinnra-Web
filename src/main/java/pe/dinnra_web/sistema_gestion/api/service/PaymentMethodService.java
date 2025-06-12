package pe.dinnra_web.sistema_gestion.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.PaymentMethodRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.PaymentMethodResponse;



public interface PaymentMethodService {
    PaymentMethodResponse create(PaymentMethodRequest request);
    PaymentMethodResponse findById(Long idPaymentMethod);
    Page<PaymentMethodResponse> findAll(Pageable pageable);
}
