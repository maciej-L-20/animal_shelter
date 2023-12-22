package bada_shelter.SpringApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query(value = "SELECT * FROM (SELECT * FROM ZWIERZETA WHERE NR_ADOPTUJACEGO IS NULL ORDER BY DBMS_RANDOM.value) WHERE ROWNUM <= 8", nativeQuery = true)
    List<Animal> findRandomAnimals();
}
