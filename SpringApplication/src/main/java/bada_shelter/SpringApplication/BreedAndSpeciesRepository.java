package bada_shelter.SpringApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public interface BreedAndSpeciesRepository extends JpaRepository<BreedAndSpecies,Long>{
    default void getAllBreedsAndSpecies(List<String> forBreeds, List<String> forSpecies) {
        List<BreedAndSpecies> breedAndSpecies = this.findAll();
        forBreeds.addAll(breedAndSpecies.stream()
                .map(BreedAndSpecies::getBreed)
                .filter(Objects::nonNull)
                .map(String::valueOf)  // Convert each breed to a String
                .distinct()
                .collect(Collectors.toList()));
        forSpecies.addAll(breedAndSpecies.stream()
                .map(BreedAndSpecies::getSpecies)
                .filter(Objects::nonNull)
                .map(String::valueOf)  // Convert each breed to a String
                .distinct()
                .collect(Collectors.toList()));
    }
    List<BreedAndSpecies> findByBreedAndSpecies(String breed,String species);
    List<BreedAndSpecies> findBySpecies(String species);
}
