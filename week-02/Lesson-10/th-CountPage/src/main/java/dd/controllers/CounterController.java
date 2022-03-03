package dd.controllers;

import dd.model.Count;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("counter")
public class CounterController {

    @ModelAttribute("counter")
    public Count setUpCounter() {
        return new Count();
    }

    @GetMapping("/")
    public String get(@ModelAttribute("counter") Count count) {
        count.increment();
        return "/index";
    }
}
