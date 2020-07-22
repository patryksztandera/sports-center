package pl.euvic.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "courts")
public class CourtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

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
