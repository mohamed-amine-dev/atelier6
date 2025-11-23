package gestionabscence.Services;

import gestionabscence.DTOs.AbsenceUpdate;
import gestionabscence.Entities.Absence;
import gestionabscence.Repositories.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    public List<Absence> findAll() {
        return absenceRepository.findAll();
    }

    public String addAbsence(Absence absence) {
        absenceRepository.save(absence);
        return "success";
    }

    public List<Absence> getAbsencesByEtudiant(Long idEtudiant) {
        return absenceRepository.findByEtudiantIdEtudiant(idEtudiant);
    }

    public Absence updateAbsence(Long idAbsence, AbsenceUpdate dto) {
        Absence abs = absenceRepository.findById(idAbsence)
                .orElseThrow(() -> new RuntimeException("Absence introuvable"));

        abs.setModule(dto.module());
        abs.setDateAbsence(dto.dateAbsence());

        return absenceRepository.save(abs);
    }

    public String deleteAbsence(Long id) {
        absenceRepository.deleteById(id);
        return "success";
    }
}
