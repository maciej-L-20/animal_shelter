package bada_shelter.SpringApplication;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "UZYTKOWNICY")
public class User {

    @Id
    @Column(name = "nazwa_uzytkownika")
    private String username;
    @Column(name = "haslo")
    private String password;
    @Column(name = "wlaczony")
    private boolean enabled;
    @Column(name = "imie")
    private String firstName;
    @Column(name = "nazwisko")
    private String lastName;
    @Column(name = "data_urodzenia")
    private Date birthDate;

    // Getters and setters
}
