package bada_shelter.SpringApplication.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "GATUNKI_RASY")
public class BreedAndSpecies {

    @Id
    @SequenceGenerator(name = "bs_id_seq", sequenceName = "SEQUENCE_NR_GATUNEK_RASA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bs_id_seq")
    @Column(name = "NR_GATUNEK_RASA")
    private Long id;

    @Column(name = "GATUNEK")
    private String species;

    @Column(name = "RASA")
    private String breed;


}
