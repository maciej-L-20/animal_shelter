package bada_shelter.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class StaffController {
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    @Autowired
    public StaffController(UserRepository userRepository,AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @GetMapping("/addStaffMember")
    public String showUserAddingPanel(Model model){
        UserAddingModel userAddingModel = new UserAddingModel();
        model.addAttribute("addingModel",userAddingModel);
        return "/staff/admin/add_user_panel";
    }

    //TODO:OGARNĄC ROLE
    @PostMapping("/addStaffMember")
    public String addStaffMember(@ModelAttribute("addingModel") UserAddingModel userAddingModel) throws ParseException {
        User addedUser = userAddingModel.getUser();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(userAddingModel.birthDateString);
        addedUser.setBirthDate(parsed);
        userRepository.save(addedUser);
        return "staff/successful_adding";
    }

}
