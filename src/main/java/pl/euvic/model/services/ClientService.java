package pl.euvic.model.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.euvic.configuration.AppUserPrincipal;
import pl.euvic.exceptions.NotFoundException;
import pl.euvic.model.entities.ClientEntity;
import pl.euvic.model.repositories.ClientRepository;
import pl.euvic.model.responses.ClientRestModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService implements UserDetailsService {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final ClientRepository clientRepository;

    public ClientService(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientRestModel> getAll() {
        return clientRepository.findAll().stream()
                .map(ClientRestModel::new)
                .collect(Collectors.toList());
    }

    public ClientRestModel getById(final Long id) {
        List<Long> list = clientRepository.findAll().stream().map(ClientEntity::getId).collect(Collectors.toList());
        if (!list.contains(id)) {
            throw new NotFoundException("Such pearson does not exist!");
        }
        return new ClientRestModel(clientRepository.getById(id));
    }

    public Long add(ClientRestModel clientRestModel) {
        return clientRepository.save(mapRestModel(clientRestModel)).getId();
    }

    private ClientEntity mapRestModel(final ClientRestModel model) {
        return new ClientEntity(model.getName(), model.getSurname(), model.getEmail(),
                passwordEncoder.encode(model.getPassword()), model.getPhone());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ClientEntity clientEntity = clientRepository.getByEmail(email);
        return new AppUserPrincipal(clientEntity);
    }
}
