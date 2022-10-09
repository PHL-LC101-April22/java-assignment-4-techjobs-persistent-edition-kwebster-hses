package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    // thymeleaf Template must use class.repository.method/field
    //event.employerRepository.name etc
    //review annotations still a bit confusing 

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("")
    public String index(@RequestParam(required = false)Integer employerId, Model model) {
        if(employerId == null) {
            model.addAttribute("employers", employerRepository.findAll());
        }
        else{
            Optional<Employer> result = employerRepository.findById(employerId);
            if(result.isEmpty()) {
            model.addAttribute("employer","Invalid EmployerId "+ employerId);
            }
            else {
                Employer employer = result.get();
                model.addAttribute("employer","Items in Employer With Id: " + employer.getName());
            }
        }
            return "employers/index";

    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        }

        employerRepository.save(newEmployer);
          //  model.addAttribute("employer",employerRepository.findAll());

        return "redirect:";
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }
}
