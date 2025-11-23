package gestionabscence.Controllers;

import gestionabscence.Entities.Departement;
import gestionabscence.Services.DepartementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Departement")
public class DepartementController {

    private final DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    // Liste
    @GetMapping("/allDepartement")
    public String list(Model model) {
        model.addAttribute("departements", departementService.findAll());
        return "departement/list";
    }

    // Formulaire ajout
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("departement", new Departement());
        return "departement/add";
    }

    // Ajouter
    @PostMapping("/addDepartement")
    public String add(@ModelAttribute Departement departement) {
        departementService.createDepartement(departement);
        return "redirect:/Departement/allDepartement";
    }

    // Formulaire update
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("departement", departementService.findOne(id));
        return "departement/edit";
    }

    // Update
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Departement departement) {
        departementService.updateDepartement(id, departement);
        return "redirect:/Departement/allDepartement";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        departementService.deleteDepartement(id);
        return "redirect:/Departement/allDepartement";
    }

    // Détails
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("departement", departementService.findOne(id));
        return "departement/detail";
    }
}
