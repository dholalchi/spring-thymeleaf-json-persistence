package de.holalchi;

import de.holalchi.application.PersonService;
import de.holalchi.domain.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class AppController {
    private final PersonService service;

    public AppController(PersonService personService) {
        this.service = personService;
    }

    private static String listAllPersonNames(Model model, PersonService service) {
        List<String> allNames = service.getAllRecords();
        model.addAttribute("allNames", allNames);
        return "list_view";
    }

    @GetMapping("/form")
    public String personForm(Model model) {
        model.addAttribute("person", new Person());
        return "form_view";
    }

    @PostMapping("/form")
    public String personSubmit(@ModelAttribute Person person, BindingResult bindingResult) throws IOException {
        if (!bindingResult.hasErrors()) {
            service.savePerson(person);
            return "confirm_view";
        }

        return "form_view";
    }

    @PutMapping("/form")
    public String updatePerson(@ModelAttribute Person person, Model model) throws IOException {
        service.savePerson(person);
        model.addAttribute("update", true);
        return "confirm_view";
    }

    @GetMapping("/list")
    public String listName(Model model) {
        return listAllPersonNames(model, service);
    }

    @GetMapping("/edit/{name}")
    public String personFormEdit(@PathVariable("name") String name, Model model) {
        Person person = service.getPerson(name);
        model.addAttribute("person", person);
        return "form_edit_view";
    }
}
