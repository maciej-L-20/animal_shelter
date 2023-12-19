package bada_shelter.SpringApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedAndSpeciesRepository extends JpaRepository<BreedAndSpecies,Long>{

}
