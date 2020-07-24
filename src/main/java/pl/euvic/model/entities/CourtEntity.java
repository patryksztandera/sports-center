package pl.euvic.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courts")
public class CourtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "courtEntity", cascade = CascadeType.ALL)
    private List<ScheduleEntity> schedule = new ArrayList<>();

    public CourtEntity(final String name){
        this.name = name;
    }

    public CourtEntity(){
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
