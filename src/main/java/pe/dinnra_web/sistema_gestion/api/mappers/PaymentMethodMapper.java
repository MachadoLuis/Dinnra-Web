package pe.dinnra_web.sistema_gestion.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.PaymentMethodRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.PaymentMethodResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.PaymentMethod;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

    @Mapping(target = "idPayment", ignore = true)
    PaymentMethod toPaymentMethod(PaymentMethodRequest request);

    @Mapping(target = "idPaymentMethod", source = "idPayment")
    PaymentMethodResponse toPaymentMethodResponse(PaymentMethod entity);
}
