package pl.euvic.model.responses;

import pl.euvic.model.entities.ClientEntity;

public class ClientRestModel {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;

    public ClientRestModel(final String name, final String surname, final String email,
                           final String password, final String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public ClientRestModel() {
    }

    public ClientRestModel(ClientEntity entity) {
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.phone = entity.getPhone();
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

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
