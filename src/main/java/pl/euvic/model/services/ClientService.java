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
import pl.euvic.model.entities.RoleEntity;
import pl.euvic.model.repositories.ClientRepository;
import pl.euvic.model.repositories.RoleRepository;
import pl.euvic.model.responses.ClientRestModel;
import pl.euvic.utils.RoleName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ClientService implements UserDetailsService {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;

    public ClientService(ClientRepository clientRepository, RoleRepository roleRepository) {
        this.clientRepository = clientRepository;
        this.roleRepository = roleRepository;
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
        List<RoleEntity> list = new ArrayList<>();
        if (model.getRole().equals("admin")) {
            list.add(roleRepository.getRoleByName(RoleName.ROLE_ADMIN));
        }
        if (model.getRole().equals("user")){
            list.add(roleRepository.getRoleByName(RoleName.ROLE_USER));
        }
        return new ClientEntity(model.getName(), model.getSurname(), model.getEmail(),
                passwordEncoder.encode(model.getPassword()), model.getPhone(), list);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new AppUserPrincipal(clientRepository.getByEmail(email));
    }
}
