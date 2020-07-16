package pl.euvic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.euvic.model.responses.ClientRestModel;
import pl.euvic.model.services.ClientService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(final ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientRestModel>> listAllClients() {
        final List<ClientRestModel> clients = clientService.getAll();

        return ResponseEntity.ok(clients);
    }

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addClient(@RequestBody final ClientRestModel client) {
        return ResponseEntity.ok(clientService.add(client));
    }
}
