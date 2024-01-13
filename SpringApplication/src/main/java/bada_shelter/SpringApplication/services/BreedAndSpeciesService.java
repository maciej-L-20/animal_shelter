package bada_shelter.SpringApplication.services;

import bada_shelter.SpringApplication.jpaRepositories.BreedAndSpeciesRepository;
import bada_shelter.SpringApplication.models.BreedAndSpecies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BreedAndSpeciesService {
    @Autowired
    BreedAndSpeciesRepository breedAndSpeciesRepository;
    public List<String>[] getAllBreedsAndSpecies() {
        List<BreedAndSpecies> breedAndSpecies = breedAndSpeciesRepository.findAll();
        List<String> breeds = breedAndSpecies.stream()
                .map(BreedAndSpecies::getBreed)
                .filter(Objects::nonNull)
                .map(String::valueOf)  // Convert each breed to a String
                .distinct()
                .collect(Collectors.toList());
        List<String> species = breedAndSpecies.stream()
                .map(BreedAndSpecies::getSpecies)
                .filter(Objects::nonNull)
                .map(String::valueOf)  // Convert each breed to a String
                .distinct()
                .collect(Collectors.toList());
        @SuppressWarnings("unchecked")  // Ignoruj ostrzeżenie dotyczące konwersji do Object[]
        List<String>[] allBreedsAndSpecies = new List[]{breeds, species};
        return allBreedsAndSpecies;
    }
}
