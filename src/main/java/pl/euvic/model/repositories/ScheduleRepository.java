package pl.euvic.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.euvic.model.entities.ScheduleEntity;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    ScheduleEntity getById(Long id);
   // ScheduleEntity setReserved(Boolean reserved);
}
