package gestionabscence.Controllers;

import gestionabscence.DTOs.EtudiantCreate;
import gestionabscence.DTOs.EtudiantForm;
import gestionabscence.DTOs.EtudiantResponse;
import gestionabscence.DTOs.EtudiantUpdate;
import gestionabscence.Services.EtudiantService;
import gestionabscence.Entities.Etudiant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Etudiant")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    // Liste des étudiants
    @GetMapping("/allEtudiant")
    public String getAllEtudiant(Model model) {
        List<EtudiantResponse> etudiants = etudiantService.findAll();
        if (etudiants == null) {
            etudiants = new ArrayList<>();
        }
        model.addAttribute("etudiants", etudiants);
        return "etudiant/list";
    }

    // Formulaire ajout
    @GetMapping("/add")
    public String addEtudiantForm(Model model) {
        model.addAttribute("etudiant", new EtudiantForm());
        return "etudiant/add";
    }

    // Ajouter Etudiant
    @PostMapping("/createEtudiant")
    public String createEtudiant(@ModelAttribute EtudiantCreate etudiant) {
        etudiantService.create(etudiant);
        return "redirect:/Etudiant/allEtudiant";
    }

    // Formulaire update
    @GetMapping("/edit/{id}")
    public String editEtudiantForm(@PathVariable Long id, Model model) {
        model.addAttribute("etudiant", etudiantService.findOne(id));
        return "etudiant/edit";
    }

    // Update Etudiant
    @PostMapping("/update/{id}")
    public String updateEtudiant(@PathVariable Long id, @ModelAttribute EtudiantUpdate etudiant) {
        etudiantService.update(id, etudiant);
        return "redirect:/Etudiant/allEtudiant";
    }

    // Supprimer Etudiant
    @GetMapping("/delete/{id}")
    public String deleteEtudiant(@PathVariable Long id) {
        etudiantService.delete(id);
        return "redirect:/Etudiant/allEtudiant";
    }

    // Affecter département
    @PostMapping("/affecter-dep")
    public String assignDepartement(
            @RequestParam Long idEtd,
            @RequestParam Long idDep) {

        etudiantService.assignDepartement(idEtd, idDep);
        return "redirect:/Etudiant/allEtudiant";
    }
}
