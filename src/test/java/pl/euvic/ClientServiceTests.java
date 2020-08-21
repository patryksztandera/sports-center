package pl.euvic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.euvic.model.repositories.ClientRepository;
import pl.euvic.model.responses.ClientRestModel;
import pl.euvic.model.services.ClientService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("h2")
class ClientServiceTests {

	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientRepository clientRepository;

	@Test
	void addClient() {
		final ClientRestModel client = new ClientRestModel("Name","Surname","name.surname@gmail.com",
				"password","+48 123 456 789");

		assertEquals(0, clientRepository.count());
		clientService.add(client);

		assertEquals(1, clientRepository.count());
	}
}
