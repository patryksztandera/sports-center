package pl.euvic.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(length = 100, unique = true)
    @Email
    private String email;

    @Column
    @JsonIgnore
    private String password;

    @Column
    @Pattern(regexp = "(\\+\\d{2} ?)?(\\d{3} \\d{3} \\d{3}|\\d{9})")
    private String phone;

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    private final List<ReservationEntity> reservation = new ArrayList<>();

    public ClientEntity(final String name, final String surname, final String email,
                        final String password, final String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public ClientEntity() {
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

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return email;
    }
}
