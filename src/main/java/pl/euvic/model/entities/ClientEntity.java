package pl.euvic.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    @Email
    private String email;

    @Column
    @Pattern(regexp = "(\\+\\d{2} ?)?(\\d{3} \\d{3} \\d{3}|\\d{9})")
    private String phone;

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    private final List<ScheduleEntity> schedule = new ArrayList<>();

    public ClientEntity(final String name, final String surname, final String email, final String phone){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public ClientEntity(){
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
