package bada_shelter.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class StaffController {
    private final BreedAndSpeciesRepository breedAndSpeciesRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public StaffController(BreedAndSpeciesRepository breedAndSpeciesRepository, AnimalRepository animalService) {
        this.animalRepository = animalService;
        this.breedAndSpeciesRepository = breedAndSpeciesRepository;
    }
    @GetMapping("/search_panel")
    public String showSearchPanel(HttpServletRequest request,Model model){
        List<String> breeds = new ArrayList<>();
        List<String> species = new ArrayList<>();
        breedAndSpeciesRepository.getAllBreedsAndSpecies(breeds,species);
        model.addAttribute("breeds",breeds);
        model.addAttribute("species",species);
        if (request.isUserInRole("ADMIN")) {
            return "/staff/admin/search_panel";
        }
        return "/staff/search_panel";
    }
    @GetMapping("/addAnimal")
    public String showAddingPanel(HttpServletRequest request,Model model){
        List<String> breeds = new ArrayList<>();
        List<String> species = new ArrayList<>();
        breedAndSpeciesRepository.getAllBreedsAndSpecies(breeds,species);
        model.addAttribute("breeds",breeds);
        model.addAttribute("species",species);
        if (request.isUserInRole("ADMIN")) {
            return "/staff/admin/add_panel";
        }
        return "/staff/add_panel";
    }
}
