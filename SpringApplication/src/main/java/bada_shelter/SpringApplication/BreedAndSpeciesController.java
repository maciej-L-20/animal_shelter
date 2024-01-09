package bada_shelter.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class BreedAndSpeciesController {
    private final BreedAndSpeciesRepository breedAndSpeciesRepository;

    @Autowired
    public BreedAndSpeciesController(BreedAndSpeciesRepository breedAndSpeciesRepository) {
        this.breedAndSpeciesRepository = breedAndSpeciesRepository;
    }

    @GetMapping("/getBreedsBySpecies/{species}")
    public List<String> getBreedsBySpecies(@PathVariable String species) {
        return breedAndSpeciesRepository.findBySpecies(species)
                .stream()
                .map(BreedAndSpecies::getBreed)
                .filter(breed -> breed != null && !breed.isEmpty())
                .distinct()
                .toList();
    }
}
