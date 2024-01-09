package bada_shelter.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.annotation.DateTimeFormat;
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
        if(request.isUserInRole("ADMIN")||request.isUserInRole("USER")) return "/staff/animal";
        return "animal";
    }

    //Metoda obsługująca stronę główną
    @RequestMapping(value = {"/index"})
    public String getIndexPage(Model model) {
        List<Animal> animals = animalRepository.findRandomAnimals();
        for (Animal animal : animals) {
            animal.findPhotoPath();
        }
        List<String> breeds = new ArrayList<>();
        List<String> species = new ArrayList<>();
        breedAndSpeciesRepository.getAllBreedsAndSpecies(breeds,species);

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
    @GetMapping("/searchAnimals")
    public String searchAnimals(@RequestParam(name = "idNumber",required = false) Integer idNumber,
            @RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "ageMin", required = false) Integer minAge,
                                @RequestParam(name = "ageMax", required = false) Integer maxAge,
                                @RequestParam(name = "massMin", required = false) Integer minMass,
                                @RequestParam(name = "massMax", required = false) Integer maxMass,
                                @RequestParam(name = "gender", required = false) String gender,
                                @RequestParam(name = "species", required = false) String species,
                                @RequestParam(name = "breed", required = false) String breed,
                                @RequestParam(value = "minAcceptanceDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String minAcceptanceDate,
                                @RequestParam(value = "maxAcceptanceDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String maxAcceptanceDate,
                                @RequestParam(value = "minLeaveDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String minLeaveDate,
                                @RequestParam(value = "maxLeaveDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String maxLeaveDate,
                                Model model){
        Long id = null;
        if(idNumber!=null) id = Long.valueOf(idNumber);
        List<Animal> searchResults = animalRepository.searchAnimals(id,name, minAge, maxAge, minMass, maxMass, gender, species, breed,minAcceptanceDate,maxAcceptanceDate,minLeaveDate,maxLeaveDate);
        model.addAttribute("animals",searchResults);
        return "/staff/search_result";

    }

    @PostMapping("/addAnimal")
    public String addAnimal(HttpServletRequest request, @RequestParam(name = "name", required = false) String name,
                            @RequestParam(name = "age") Integer age,
                            @RequestParam(name = "gender") String gender,
                            @RequestParam(name = "species") String species,
                            @RequestParam(name = "breed",required = false) String breed,
                            @RequestParam(value = "acceptanceDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String acceptanceDate,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "isVaccinated") char isVaccinated,
                            @RequestParam(value = "isNeutered") char isNeutered) throws ParseException {
        Animal animal = new Animal();
        animal.setName(name);
        animal.setAge(age);
        animal.setGender(gender);
        animal.setDescription(description);
        animal.setIsNeutered(isNeutered);
        animal.setIsVaccinated(isVaccinated);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        animal.setAcceptanceDate(dateFormat.parse(acceptanceDate));
        List<BreedAndSpecies> breedAndSpecies;
        if(breed == "") breedAndSpecies = breedAndSpeciesRepository.findBySpecies(species);
        else breedAndSpecies = breedAndSpeciesRepository.findByBreedAndSpecies(breed,species);
        if(breedAndSpecies.isEmpty()) {
            BreedAndSpecies addedBreedAndSpecies = new BreedAndSpecies();
            addedBreedAndSpecies.setSpecies(species);
            addedBreedAndSpecies.setBreed(breed);
            breedAndSpeciesRepository.save(addedBreedAndSpecies);
            breedAndSpecies = breedAndSpeciesRepository.findByBreedAndSpecies(breed, species);
        }
        animal.setBreedAndSpecies(breedAndSpecies.get(0));

        animalRepository.save(animal);
        return "/staff/successful_adding";
    }
}