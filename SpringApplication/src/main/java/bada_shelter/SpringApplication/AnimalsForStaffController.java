package bada_shelter.SpringApplication;

import bada_shelter.SpringApplication.Animal;
import bada_shelter.SpringApplication.BreedAndSpecies;
import bada_shelter.SpringApplication.AnimalRepository;
import bada_shelter.SpringApplication.BreedAndSpeciesRepository;
import bada_shelter.SpringApplication.AnimalService;
import bada_shelter.SpringApplication.BreedAndSpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class AnimalsForStaffController {
    private BreedAndSpeciesService breedAndSpeciesService;
    private AnimalService animalService;
    private AnimalRepository animalRepository;
    private BreedAndSpeciesRepository breedAndSpeciesRepository;
    @Autowired

    public AnimalsForStaffController(BreedAndSpeciesService breedAndSpeciesService, AnimalService animalService, AnimalRepository animalRepository, BreedAndSpeciesRepository breedAndSpeciesRepository) {
        this.breedAndSpeciesService = breedAndSpeciesService;
        this.animalService = animalService;
        this.animalRepository = animalRepository;
        this.breedAndSpeciesRepository = breedAndSpeciesRepository;
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
                            @RequestParam(name = "mass") Integer mass,
                            @RequestParam(name = "gender") String gender,
                            @RequestParam(name = "species") String species,
                            @RequestParam(name = "breed",required = false) String breed,
                            @RequestParam(value = "acceptanceDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String acceptanceDate,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "isVaccinated") char isVaccinated,
                            @RequestParam(value = "isNeutered") char isNeutered, Model model) throws ParseException {
        Animal animal = new Animal();
        animal.setName(formatText(name));
        animal.setAge(age);
        animal.setMass(mass);
        animal.setGender(gender);
        animal.setDescription(description);
        animal.setIsNeutered(isNeutered);
        animal.setIsVaccinated(isVaccinated);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        animal.setAcceptanceDate(dateFormat.parse(acceptanceDate));
        List<BreedAndSpecies> breedAndSpecies;
        if(breed == "") breedAndSpecies = breedAndSpeciesRepository.findBySpecies(species);
        else breedAndSpecies = breedAndSpeciesRepository.findByBreedAndSpecies(formatText(breed),species);
        if(breedAndSpecies.isEmpty()) {
            BreedAndSpecies addedBreedAndSpecies = new BreedAndSpecies();
            addedBreedAndSpecies.setSpecies(species);
            addedBreedAndSpecies.setBreed(formatText(breed));
            breedAndSpeciesRepository.save(addedBreedAndSpecies);
            breedAndSpecies = breedAndSpeciesRepository.findByBreedAndSpecies(formatText(breed), species);
        }
        animal.setBreedAndSpecies(breedAndSpecies.get(0));
        model.addAttribute("successType", "addAnimal");
        animalRepository.save(animal);
        return "/staff/successful_operation";
    }
    @GetMapping("/search_panel")
    public String showSearchPanel(Model model){
        List <String> [] allBreedsAndSpecies = breedAndSpeciesService.getAllBreedsAndSpecies();
        model.addAttribute("breeds",allBreedsAndSpecies[0]);
        model.addAttribute("species",allBreedsAndSpecies[1]);
        return "/staff/search_panel";
    }
    @GetMapping("/addAnimal")
    public String showAddingPanel(Model model){
        List <String> [] allBreedsAndSpecies = breedAndSpeciesService.getAllBreedsAndSpecies();
        model.addAttribute("breeds",allBreedsAndSpecies[0]);
        model.addAttribute("species",allBreedsAndSpecies[1]);
        Animal added = new Animal();
        model.addAttribute("added",added);
        return "/staff/add_panel";
    }
    @PostMapping("/animal/{id}")
    public String deleteAnimal(@PathVariable Long id, Model model) {
        animalRepository.deleteById(id);
        model.addAttribute("successType", "deleteAnimal");
        return "/staff/successful_operation";
    }
    @GetMapping("/editAnimal/{id}")
    public String showAnimalEditPanel(@PathVariable Long id, Model model, HttpServletRequest request) {
        List <String> [] allBreedsAndSpecies = breedAndSpeciesService.getAllBreedsAndSpecies();
        model.addAttribute("breeds",allBreedsAndSpecies[0]);
        model.addAttribute("species",allBreedsAndSpecies[1]);
        Animal animalBefore = animalRepository.getById(id);
        model.addAttribute("animalBefore", animalBefore);
        return "/staff/editAnimal_panel";
    }
    @PostMapping("/editAnimal/{id}")
    public String editAnimal(@PathVariable Long id,
                             @RequestParam(name = "name", required = false) String name,
                             @RequestParam(name = "age") Integer age,
                             @RequestParam(name = "mass") Integer mass,
                             @RequestParam(value = "description", required = false) String description,
                             @RequestParam(value = "isVaccinated") char isVaccinated,
                             @RequestParam(value = "isNeutered") char isNeutered, Model model) throws ParseException {
        Animal animal = animalRepository.getById(id);

        animal.setName(formatText(name));
        animal.setAge(age);
        animal.setMass(mass);
        animal.setIsVaccinated(isVaccinated);
        animal.setIsNeutered(isNeutered);
        animal.setDescription(description);

        animalRepository.save(animal);

        model.addAttribute("successType", "editAnimal");
        return "/staff/successful_operation";
    }
    public static String formatText(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}
