package pe.dinnra_web.sistema_gestion.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.dinnra_web.sistema_gestion.api.exceptions.UserClientNotFoundException;
import pe.dinnra_web.sistema_gestion.api.mappers.UserClientMapper;
import pe.dinnra_web.sistema_gestion.api.model.dto.request.UserClientRequest;
import pe.dinnra_web.sistema_gestion.api.model.dto.response.UserClientResponse;
import pe.dinnra_web.sistema_gestion.api.model.entity.UserClient;
import pe.dinnra_web.sistema_gestion.api.repository.UserClientRepository;
import pe.dinnra_web.sistema_gestion.api.service.UserClientService;

@Service
@Transactional
@RequiredArgsConstructor
public class UserClientServiceImpl implements UserClientService {
    private final UserClientRepository userClientRepository;
    private final UserClientMapper userClientMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserClientResponse create(UserClientRequest request) {
        UserClient userClient = userClientMapper.toUserClient(request);
        userClient.setPassword(passwordEncoder.encode(request.getPassword()));
        UserClient saveUserClient = userClientRepository.save(userClient);
        return userClientMapper.toUserClientResponse(saveUserClient);
    }

    @Override
    @Transactional(readOnly = true)
    public UserClientResponse findById(Long idUserClient) {
        return userClientRepository.findById(idUserClient)
                .map(userClientMapper::toUserClientResponse)
                .orElseThrow(() -> new UserClientNotFoundException("Usuario de Cliente no encontrado con ID: " + idUserClient));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserClientResponse> findAll(Pageable pageable) {
        return userClientRepository.findAll(pageable)
                .map(userClientMapper::toUserClientResponse);
    }

    @Override
    public void deleteById(Long idUserClient) {
        if (!userClientRepository.existsById(idUserClient)){
            throw new UserClientNotFoundException("Usuario de Cliente no encontrado con ID: " + idUserClient);
        }
        userClientRepository.deleteById(idUserClient);
    }
}
