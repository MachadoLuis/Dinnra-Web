package pe.dinnra_web.sistema_gestion.api.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.dinnra_web.sistema_gestion.api.mappers.ContactServiceMapper;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.ContactServiceRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ContactServiceResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.ContactService;
import pe.dinnra_web.sistema_gestion.api.repository.ContactServiceRepository;
import pe.dinnra_web.sistema_gestion.api.service.ContactServiceService;

@Service
@Transactional
@RequiredArgsConstructor
public class ContactServiceServiceImpl implements ContactServiceService {

    private final ContactServiceRepository contactServiceRepository;
    private final ContactServiceMapper contactServiceMapper;

    @Override
    public ContactServiceResponse create(ContactServiceRequest request) {
        ContactService contactService = contactServiceMapper.toContactService(request);
        ContactService saved = contactServiceRepository.save(contactService);
        return contactServiceMapper.toContactServiceResponse(saved);
    }

    @Override
    public ContactServiceResponse findById(Long idContactService) {
        ContactService found = contactServiceRepository.findById(idContactService)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el contacto con ID: " + idContactService));
        return contactServiceMapper.toContactServiceResponse(found);
    }

    @Override
    public Page<ContactServiceResponse> findAll(Pageable pageable){
        return contactServiceRepository.findAll(pageable)
                .map(contactServiceMapper::toContactServiceResponse);
    }
}
