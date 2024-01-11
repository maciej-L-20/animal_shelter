package bada_shelter.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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

    // Metoda obsługująca żądanie GET dla spersonalizowanej strony o zwierzęciu
    @GetMapping("/animal/{id}")
    public String showAnimalDetailPage(@PathVariable Long id, Model model,HttpServletRequest request) {
        Animal animal = animalRepository.getById(id);
        model.addAttribute("animal", animal);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))
                || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER")))) {
            return "staff/animal";
        } else {
            return "animal";
        }
    }

    //TODO: Uzupełnić kryteria i przygotować widok
    @GetMapping("/searchToAdoption")
    public String searchToAnimalsAdoption(@RequestParam(name = "name", required = false) String name,
                                          @RequestParam(name = "ageMin", required = false) Integer minAge,
                                          @RequestParam(name = "ageMax", required = false) Integer maxAge,
                                          @RequestParam(name = "massMin", required = false) Integer minMass,
                                          @RequestParam(name = "massMax", required = false) Integer maxMass,
                                          @RequestParam(name = "gender", required = false) String gender,
                                          @RequestParam(name = "species", required = false) String species,
                                          @RequestParam(name = "breed", required = false) String breed,
                                          Model model) {

        List<Animal> searchResults = animalRepository.searchAnimalsForClients(name, minAge, maxAge, minMass, maxMass, gender, species, breed).stream().filter(p -> p.getLeaveDate() == null).toList();

        model.addAttribute("animals", searchResults);
        return "fittingAnimals";
    }
}