package by.bsuir.shigalo7.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String phone;
    private String password;
    private String mail;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User(String name, String password, boolean active, String surname, String phone, String mail) {
        this.name = name;
        this.password = password;
        this.active = active;
        this.surname = surname;
        this.phone = phone;
        this.mail = mail;
    }

    public User() {
    }
}
