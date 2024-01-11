package bada_shelter.SpringApplication;

import bada_shelter.SpringApplication.Animal;
import bada_shelter.SpringApplication.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> findAnimalToDisplay(){
        List<Animal> animals = animalRepository.findRandomAnimals();
        for (Animal animal : animals) {
            animal.findPhotoPath();
        }
        return animals;
    }
}
