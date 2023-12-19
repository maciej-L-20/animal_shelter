package bada_shelter.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BreedAndSpeciesController {
    private final BreedAndSpeciesRepository breedAndSpeciesRepository;

    @Autowired
    public BreedAndSpeciesController(BreedAndSpeciesRepository breedAndSpeciesRepository) {
        this.breedAndSpeciesRepository = breedAndSpeciesRepository;
    }

    // Endpoint do pobierania listy wszystkich zwierząt
    @RequestMapping(value={"/breedAndSpecies"})
    public List<BreedAndSpecies> getAllBreedAndSpecies() {
        List<BreedAndSpecies> breedAndSpecies= breedAndSpeciesRepository.findAll();
        return breedAndSpecies;
    }
}
