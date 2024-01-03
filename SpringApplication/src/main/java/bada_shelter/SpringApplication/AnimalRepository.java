package bada_shelter.SpringApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query(value = "SELECT * FROM (SELECT * FROM ZWIERZETA WHERE NR_ADOPTUJACEGO IS NULL ORDER BY DBMS_RANDOM.value) WHERE ROWNUM <= 8", nativeQuery = true)
    List<Animal> findRandomAnimals();

    List<Animal> findAnimalsByNameContainingIgnoreCase(String name);

    //TODO: ROZBUDOWAĆ DLA ADMINA O ADOPTUJACYCH
    @Query("SELECT a FROM Animal a " +
            "WHERE (:name IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:minAge IS NULL OR a.age >= :minAge) " +
            "AND (:maxAge IS NULL OR a.age <= :maxAge) " +
            "AND (:gender IS NULL OR LOWER(a.gender) = LOWER(:gender)) " +
            "AND (:species IS NULL OR LOWER(a.breedAndSpecies.species) = LOWER(:species)) " +
            "AND (:breed IS NULL OR LOWER(a.breedAndSpecies.breed) = LOWER(:breed))")
    List<Animal> searchAnimals(@Param("name") String name,
                               @Param("minAge") Integer minAge,
                               @Param("maxAge") Integer maxAge,
                               @Param("gender") String gender,
                               @Param("species") String species,
                               @Param("breed") String breed);
}
