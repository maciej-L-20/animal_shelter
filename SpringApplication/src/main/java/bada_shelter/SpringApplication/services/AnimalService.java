package bada_shelter.SpringApplication.services;

import bada_shelter.SpringApplication.jpaRepositories.AnimalRepository;
import bada_shelter.SpringApplication.models.Animal;
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
