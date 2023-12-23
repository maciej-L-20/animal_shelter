package bada_shelter.SpringApplication;

import lombok.Data;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import javax.persistence.*;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

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

    @Column(name = "DATA_PRZYJECIA")
    private Date acceptanceDate;

    @Column(name = "DATA_OPUSZCZENIA")
    private Date leaveDate;

    @Column(name = "CZY_SZCZEPIONY")
    private char isVaccinated;

    @Column(name = "CZY_KASTROWANY")
    private char isNeutered;

    @Column(name = "OPIS")
    private String description;

    //TODO: dodać adoptującego

    public String findPhotoPath() {
        String base64Photo = "";
        try {
            ClassPathResource resource = new ClassPathResource("static/images/" + this.getName() + ".png");
            byte[] photoBytes = StreamUtils.copyToByteArray(resource.getInputStream());
            base64Photo = Base64.getEncoder().encodeToString(photoBytes);
            this.setPhotoPath(base64Photo);
        } catch (IOException e) {
            e.printStackTrace(); // Obsłuż błąd ładowania zdjęcia
        }
        return base64Photo;
    }

}

