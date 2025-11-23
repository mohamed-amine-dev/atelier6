package gestionabscence.Controllers;

import gestionabscence.DTOs.AbsenceUpdate;
import gestionabscence.DTOs.EtudiantResponse;
import gestionabscence.Entities.Absence;
import gestionabscence.Entities.Etudiant;
import gestionabscence.Services.AbsenceService;
import gestionabscence.Services.EtudiantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Absence")
public class AbsenceController {

    private final AbsenceService absenceService;
    private final EtudiantService etudiantService;

    public AbsenceController(AbsenceService absenceService, EtudiantService etudiantService) {
        this.absenceService = absenceService;
        this.etudiantService = etudiantService;
    }

    // Liste
    @GetMapping("/all")
    public String list(Model model) {
        model.addAttribute("absences", absenceService.findAll());
        return "absence/list";
    }

    // Formulaire ajout
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("absence", new Absence());
        model.addAttribute("etudiants", etudiantService.findAll());
        return "absence/add";
    }



    // Ajouter
    @PostMapping("/add")
    public String add(@ModelAttribute Absence absence) {
        absenceService.addAbsence(absence);
        return "redirect:/Absence/all";
    }

    // Formulaire update
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("abs", absenceService.getAbsencesByEtudiant(id));
        return "absence/edit";
    }

    // Update
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute AbsenceUpdate dto) {
        absenceService.updateAbsence(id, dto);
        return "redirect:/Absence/all";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        absenceService.deleteAbsence(id);
        return "redirect:/Absence/all";
    }
}
