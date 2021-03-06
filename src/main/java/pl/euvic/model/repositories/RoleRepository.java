package pl.euvic.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.euvic.model.entities.RoleEntity;
import pl.euvic.utils.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity getRoleByName(RoleName name);
}
