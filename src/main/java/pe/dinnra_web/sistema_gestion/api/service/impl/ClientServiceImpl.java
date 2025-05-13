package pe.dinnra_web.sistema_gestion.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.dinnra_web.sistema_gestion.api.exceptions.ClientNotFoundException;
import pe.dinnra_web.sistema_gestion.api.mappers.ClientMapper;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.ClientRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ClientDetailResponse;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.ClientResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.Client;
import pe.dinnra_web.sistema_gestion.api.repository.ClientRepository;
import pe.dinnra_web.sistema_gestion.api.service.ClientService;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDetailResponse create (ClientRequest request) {
        Client client = clientMapper.toClient(request);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toClientDetailResponse(savedClient);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDetailResponse findById (Long idClient) {
        return clientRepository.findById(idClient)
                .map(clientMapper::toClientDetailResponse)
                .orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado con ID: " + idClient));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientResponse> findAll (Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(clientMapper::toClientResponse);
    }

    @Override
    public ClientDetailResponse updateById (Long idClient, ClientRequest request) {
        if (!clientRepository.existsById(idClient)){
            throw new ClientNotFoundException("Cliente no encontrado con ID: " + idClient);
        }
        Client client = clientRepository.findById(idClient)
                        .orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado con ID: " + idClient));
        clientMapper.update(client, request);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toClientDetailResponse(savedClient);
    }

    @Override
    public void deleteById (Long idClient) {
        if (!clientRepository.existsById(idClient)){
            throw new ClientNotFoundException("Cliente no encontrado con ID: " + idClient);
        }
        clientRepository.deleteById(idClient);
    }
}
