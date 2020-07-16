package pl.euvic.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.euvic.model.entities.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity,Long> {

}
