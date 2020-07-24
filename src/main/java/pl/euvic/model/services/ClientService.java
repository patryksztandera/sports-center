package pl.euvic.model.services;

import org.springframework.stereotype.Service;
import pl.euvic.model.entities.ClientEntity;
import pl.euvic.model.repositories.ClientRepository;
import pl.euvic.model.responses.ClientRestModel;
import pl.euvic.model.responses.CourtRestModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

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
        return new ClientRestModel(clientRepository.getById(id));
    }

    public Long add(ClientRestModel clientRestModel){
        return clientRepository.save(mapRestModel(clientRestModel)).getId();
    }

    private ClientEntity mapRestModel(final ClientRestModel model){
        return new ClientEntity(model.getName(),model.getSurname(),model.getEmail(),model.getPhone());
    }

}
