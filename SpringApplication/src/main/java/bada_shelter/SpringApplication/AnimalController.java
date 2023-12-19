package bada_shelter.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


}