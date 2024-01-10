package bada_shelter.SpringApplication;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
public class AppController implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/animals").setViewName("animals");
        registry.addViewController("/").setViewName("redirect:/index");
        registry.addViewController("/staff/search_panel").setViewName("staff/search_panel");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/main_staff").setViewName("staff/main_staff");
        registry.addViewController("/animal").setViewName("animal");
        registry.addViewController("/staff/search_result").setViewName("staff/search_result");
    }
    @Controller
    public class DashboardController {

        @RequestMapping("/main")
        public String defaultAfterLogin(HttpServletRequest request) {
            if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
                return "redirect:/main_staff";
            } else {
                return "redirect:/main_staff";
            }
        }
        @RequestMapping(value={"/main_staff"})
        public String showUserPage(Model model) {
            return "staff/main_staff";
        }
    }
}

