package bada_shelter.SpringApplication;

import javax.persistence.*;

public class Authority {

    @ManyToOne
    @JoinColumn(name="nazwa_uzytkownika")
    private User user;
    @Column(name="uprawnienie")
    private String authority;

    // Getters and setters
}

