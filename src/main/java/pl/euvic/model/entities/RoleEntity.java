package pl.euvic.model.entities;

import pl.euvic.utils.RoleName;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    private List<ClientEntity> users;

    public RoleEntity(RoleName name) {
        this.name = name;
    }

    public RoleEntity() {
    }

    public Long getId() {
        return id;
    }

    public RoleName getName() {
        return name;
    }
}
