package pe.dinnra_web.sistema_gestion.api.controllers.RestControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.ContactServiceRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ContactServiceResponse;
import pe.dinnra_web.sistema_gestion.api.service.impl.ContactServiceServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact-service")
public class ContactServiceController {
    private final ContactServiceServiceImpl contactServiceService;

    @PostMapping
    public ResponseEntity<ContactServiceResponse> create(@RequestBody ContactServiceRequest request) {
        ContactServiceResponse response = contactServiceService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactServiceResponse> findById(@PathVariable Long id) {
        ContactServiceResponse response = contactServiceService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ContactServiceResponse>> findAll(Pageable pageable) {
        Page<ContactServiceResponse> page = contactServiceService.findAll(pageable);
        return ResponseEntity.ok(page);
    }
}
