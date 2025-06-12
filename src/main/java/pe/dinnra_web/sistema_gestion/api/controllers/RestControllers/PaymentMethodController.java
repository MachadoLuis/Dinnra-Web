package pe.dinnra_web.sistema_gestion.api.controllers.RestControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.PaymentMethodRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.PaymentMethodResponse;
import pe.dinnra_web.sistema_gestion.api.service.impl.PaymentMethodServiceImpl;

@Tag(name = "payment_methods", description = "API para gestion de métodos de pago")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment_method")
public class PaymentMethodController {

    private final PaymentMethodServiceImpl paymentMethodService;

    @Operation(summary = "Crear método de pago")
    @PostMapping
    public ResponseEntity<PaymentMethodResponse> create(
            @Valid @RequestBody PaymentMethodRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(paymentMethodService.create(request));
    }

    @Operation(summary = "Obtener método de pago por ID")
    @GetMapping("/{idPaymentMethod}")
    public ResponseEntity<PaymentMethodResponse> findById(
            @PathVariable Long idPaymentMethod) {
        return ResponseEntity.ok(paymentMethodService.findById(idPaymentMethod));
    }

    @Operation(summary = "Listar métodos de pago paginados")
    @GetMapping
    public ResponseEntity<Page<PaymentMethodResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(paymentMethodService.findAll(pageable));
    }
}