package bada_shelter.SpringApplication;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ZWIERZETA")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NR_ZWIERZECIA")
    private Long id;

    @Column(name = "MASA")
    private int mass;

    @Column(name = "WIEK")
    private int age;

    @Column(name = "IMIE")
    private String name;

    @Column(name = "PLEC")
    private String gender;

    @ManyToOne
    @JoinColumn(name="NR_GATUNEK_RASA")
    private BreedAndSpecies breedAndSpecies;

    @Transient
    private String photoPath;


}

