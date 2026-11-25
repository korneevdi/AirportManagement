package airport.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class HomeController {

    @Value("${airport.home.name}")
    private String airportName;

    @GetMapping("/")
    public String getHomePage(Model model) {

        model.addAttribute(
                "Greetings",
                "Welcome to the " + airportName + "!"
        );

        model.addAttribute(
                "currentTime",
                "Current time: " + LocalDateTime.now()
        );

        return "home/home";
    }
}
