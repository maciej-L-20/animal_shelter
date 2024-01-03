package bada_shelter.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class AnimalController {
    private final BreedAndSpeciesRepository breedAndSpeciesRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalController(BreedAndSpeciesRepository breedAndSpeciesRepository, AnimalRepository animalService) {
        this.animalRepository = animalService;
        this.breedAndSpeciesRepository = breedAndSpeciesRepository;
    }

    // Endpoint do pobierania listy wszystkich zwierząt
    @RequestMapping(value={"/animals"})
    public String getAllAnimals(Model model) {
        List<Animal> animals = animalRepository.findAll();
        model.addAttribute("animals", animals);
        return "animals";
    }

    // Metoda obsługująca żądanie GET dla spersonalizowanej strony o zwierzęciu
    @GetMapping("/animal/{id}")
    public String showAnimalDetailPage(@PathVariable Long id, Model model) {
        Animal animal = animalRepository.getById(id);
        model.addAttribute("animal", animal);
        return "animal";
    }

    //Metoda obsługująca stronę główną
    @RequestMapping(value={"/index"})
    public String getIndexPage(Model model) {
        List<Animal> animals = animalRepository.findRandomAnimals();
        for (Animal animal : animals) {
            animal.findPhotoPath();
        }
        List<BreedAndSpecies> breedAndSpecies = breedAndSpeciesRepository.findAll();
        List<String> breeds = breedAndSpecies.stream().map(p->p.getBreed()).distinct().filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<String> species = breedAndSpecies.stream().map(p->p.getSpecies()).distinct().filter(Objects::nonNull)
                .collect(Collectors.toList());

        model.addAttribute("animals", animals);
        model.addAttribute("breeds", breeds);
        model.addAttribute("species", species);
        return "index";
    }

    //TODO: Uzupełnić kryteria i przygotować widok
    @GetMapping("/searchToAdoption")
    public String searchToAnimalsAdoption(@RequestParam(name = "name", required = false) String name,
                         @RequestParam(name = "ageMin", required = false) Integer minAge,
                         @RequestParam(name = "ageMax", required = false) Integer maxAge,
                         @RequestParam(name = "gender", required = false) String gender,
                         @RequestParam(name = "species", required = false) String species,
                         @RequestParam(name = "breed", required = false) String breed,
                         Model model) {

        List<Animal> searchResults = animalRepository.searchAnimals(name, minAge, maxAge, gender, species, breed).stream().filter(p-> p.getLeaveDate() == null).toList();

        model.addAttribute("animals", searchResults);
        return "animals";
    }

}