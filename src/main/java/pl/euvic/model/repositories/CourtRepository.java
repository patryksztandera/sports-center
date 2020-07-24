package pl.euvic.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.euvic.model.entities.CourtEntity;

@Repository
public interface CourtRepository extends JpaRepository<CourtEntity, Long> {

    CourtEntity getById(Long id);
}
