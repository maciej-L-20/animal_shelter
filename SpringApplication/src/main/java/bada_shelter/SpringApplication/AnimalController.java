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

@Controller
public class AnimalController {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalController(AnimalRepository animalService) {
        this.animalRepository = animalService;
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
        model.addAttribute("animals", animals);
        return "index";
    }

    //TODO: Uzupełnić kryteria i przygotować widok
    @GetMapping("/search")
    public String search(@RequestParam(name = "name", required = false) String name, Model model) {
        List<Animal> searchResults = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            searchResults = animalRepository.findAnimalsByNameContainingIgnoreCase(name);
        }
        model.addAttribute("animals",searchResults);
        return "animals";
    }

}