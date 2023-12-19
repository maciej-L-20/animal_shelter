package bada_shelter.SpringApplication;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "GATUNKI_RASY")
public class BreedAndSpecies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NR_GATUNEK_RASA")
    private Long id;

    @Column(name = "GATUNEK")
    private String species;

    @Column(name = "RASA")
    private String breed;


}
