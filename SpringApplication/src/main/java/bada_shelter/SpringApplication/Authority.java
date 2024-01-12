package bada_shelter.SpringApplication;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "uprawnienia")
public class Authority {

    @Id
    @SequenceGenerator(name = "authority_id_seq", sequenceName = "sequence_id_uprawnienia", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_id_seq")
    @Column(name="id_uprawnienia")
    private Long id;
    @ManyToOne
    @JoinColumn(name="nazwa_uzytkownika")
    private User user;
    @Column(name="uprawnienie")
    private String authorityName;

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
}

