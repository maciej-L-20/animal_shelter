package bada_shelter.SpringApplication;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return "redirect:/error/403";
            }
            else if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "redirect:/error/404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "redirect:/error/500";
            }
            else if(statusCode == HttpStatus.GATEWAY_TIMEOUT.value()) {
                return "redirect:/error/504";
            }
            else {
                return "redirect:/error/other";
            }
        }
        return "redirect:/error/other";
    }
    @GetMapping("/error/{errorCode}")
    public String showErrorPage(Model model, @PathVariable String errorCode){
        model.addAttribute("errorCode",errorCode);
        return "/errorPage";
    }
}
