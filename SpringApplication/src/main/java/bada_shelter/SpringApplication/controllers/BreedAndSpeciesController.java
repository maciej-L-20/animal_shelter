package bada_shelter.SpringApplication.controllers;

import bada_shelter.SpringApplication.jpaRepositories.BreedAndSpeciesRepository;
import bada_shelter.SpringApplication.models.BreedAndSpecies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
