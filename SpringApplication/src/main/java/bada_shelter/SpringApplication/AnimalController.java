package bada_shelter.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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


    @RequestMapping(value={"/index"})
    public String getIndexPage(Model model) {
        List<Animal> animals = animalRepository.findRandomAnimals();
        for (Animal animal : animals) {
            try {
                ClassPathResource resource = new ClassPathResource("static/photos/" + animal.getName() + ".png");
                byte[] photoBytes = StreamUtils.copyToByteArray(resource.getInputStream());
                String base64Photo = Base64.getEncoder().encodeToString(photoBytes);
                animal.setPhotoPath(base64Photo);
            } catch (IOException e) {
                e.printStackTrace(); // Obsłuż błąd ładowania zdjęcia
            }
        }
        model.addAttribute("animals", animals);
        return "index";
    }


}