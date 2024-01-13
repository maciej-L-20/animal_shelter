package bada_shelter.SpringApplication.controllers;

import bada_shelter.SpringApplication.models.Animal;
import bada_shelter.SpringApplication.services.AnimalService;
import bada_shelter.SpringApplication.services.BreedAndSpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    private final AnimalService animalService;
    private final BreedAndSpeciesService breedAndSpeciesService;
    @Autowired
    public IndexController(AnimalService animalService, BreedAndSpeciesService breedAndSpeciesService) {
        this.animalService = animalService;
        this.breedAndSpeciesService = breedAndSpeciesService;
    }
    @RequestMapping(value = {"/index"})
    public String getIndexPage(Model model) {
        List<Animal> animals = animalService.findAnimalToDisplay();
        List<String>[] breedsAndSpecies = breedAndSpeciesService.getAllBreedsAndSpecies();

        model.addAttribute("animals", animals);
        model.addAttribute("breeds", breedsAndSpecies[0]);
        model.addAttribute("species", breedsAndSpecies[1]);
        return "index";
    }
}
