package by.training.finalproject.model.entity;

import by.training.finalproject.model.Entity;
//import lombok.*;

/*@Getter@Setter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)*/
public class Person extends Entity {
    private String surname;
    private String name;
    private String email;
    private String phone;
    private String passwordHash;
    private Role role;

    public Person(){}

    public Person(int id, String surname, String name, String email, String phone, String passwordHash, Role role) {
        super(id);
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
