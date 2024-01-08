package bada_shelter.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class BreedAndSpeciesController {
    private final BreedAndSpeciesRepository breedAndSpeciesRepository;

    @Autowired
    public BreedAndSpeciesController(BreedAndSpeciesRepository breedAndSpeciesRepository) {
        this.breedAndSpeciesRepository = breedAndSpeciesRepository;
    }

}
