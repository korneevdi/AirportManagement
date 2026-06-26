package airport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class HomeController {

    private static final String AIRPORT_NAME = "Caribbean Island Airport";

    @GetMapping("/")
    public String getHomePage(Model model) {

        model.addAttribute(
                "Greetings",
                "Welcome to the " + AIRPORT_NAME + "!"
        );

        model.addAttribute(
                "currentTime",
                "Current time: " + LocalDateTime.now()
        );

        return "home";
    }
}
