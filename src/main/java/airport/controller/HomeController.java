package airport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(Model model) {

        model.addAttribute(
                "airportName",
                "Welcome to the Caribbean Island Airport!"
        );

        model.addAttribute(
                "currentTime",
                "Current time: " + LocalDateTime.now()
        );

        return "home";
    }
}
