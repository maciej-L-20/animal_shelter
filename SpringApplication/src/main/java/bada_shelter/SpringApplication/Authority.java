package bada_shelter.SpringApplication;

import javax.persistence.*;

@Entity
public class Authority {

    @Id
    @Column(name="id_uprawnienia")
    private Long id;
    @ManyToOne
    @JoinColumn(name="nazwa_uzytkownika")
    private User user;
    @Column(name="uprawnienie")
    private String authority;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}

