package pl.euvic.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles;

    public ClientEntity(String name, String surname, @Email String email,
                        String password, @Pattern(regexp = "(\\+\\d{2} ?)?(\\d{3} \\d{3} \\d{3}|\\d{9})") String phone,
                        List<RoleEntity> roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.roles = roles;
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

    public List<RoleEntity> getRoles() {
        return roles;
    }
}
