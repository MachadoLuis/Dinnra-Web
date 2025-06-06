package pe.dinnra_web.sistema_gestion.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.dinnra_web.sistema_gestion.api.mappers.PaymentMethodMapper;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.PaymentMethodRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.PaymentMethodResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.PaymentMethod;
import pe.dinnra_web.sistema_gestion.api.repository.PaymentMethodRepository;
import pe.dinnra_web.sistema_gestion.api.service.PaymentMethodService;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodMapper paymentMethodMapper;
    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethodResponse create(PaymentMethodRequest request) {
        PaymentMethod paymentMethod = paymentMethodMapper.toPaymentMethod(request);
        PaymentMethod savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        return paymentMethodMapper.toPaymentMethodResponse(savedPaymentMethod);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentMethodResponse findById(Long idPaymentMethod) {
        return paymentMethodRepository.findById(idPaymentMethod)
                .map(paymentMethodMapper::toPaymentMethodResponse)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado con ID: " + idPaymentMethod));
    }

    
}
