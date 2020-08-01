package pl.euvic.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.euvic.model.entities.ReservationEntity;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
}
