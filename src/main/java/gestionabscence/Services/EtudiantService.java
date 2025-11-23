package gestionabscence.Services;

import gestionabscence.DTOs.EtudiantCreate;
import gestionabscence.DTOs.EtudiantResponse;
import gestionabscence.DTOs.EtudiantUpdate;
import gestionabscence.Entities.Departement;
import gestionabscence.Entities.Etudiant;
import gestionabscence.Repositories.DepartementRepository;
import gestionabscence.Repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private DepartementRepository departementRepository;

    public Etudiant create(EtudiantCreate etudiant) {
        Etudiant etd = new Etudiant();
        etd.setNom(etudiant.nom());
        etd.setPrenom(etudiant.prenom());
        etd.setDate(etudiant.date());
        etd.setClasse(etudiant.classe());
        etudiantRepository.save(etd);
        return etd;
    }

    public Etudiant update(Long id, EtudiantUpdate etudiant) {
        Etudiant etd = etudiantRepository.findById(id).orElse(null);
        if(etd == null) {
            throw new RuntimeException("Aucun étudiant avec cet ID");
        }
        etd.setNom(etudiant.nom());
        etd.setPrenom(etudiant.prenom());
        etd.setDate(etudiant.date());
        etd.setClasse(etudiant.classe());
        etudiantRepository.save(etd);
        return etd;
    }

    public Void delete(Long id) {
        Etudiant etd = etudiantRepository.findById(id).get();
        if(etd.getNom().equals("null")){
            throw new RuntimeException("Aucun étudiant avec cet ID");
        }
        etudiantRepository.delete(etd);
        return null;
    }

    public EtudiantResponse toResponse(Etudiant etd) {
        return new EtudiantResponse(
                etd.getIdEtudiant(),
                etd.getNom(),
                etd.getPrenom(),
                etd.getDate(),
                etd.getClasse(),
                etd.getDepartement() != null ? etd.getDepartement().getId_departement() : null
        );
    }

    public EtudiantResponse findOne(Long id) {
        Etudiant etd = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        return toResponse(etd);
    }

    public List<EtudiantResponse> findAll() {
        return etudiantRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public String assignDepartement(Long idEtudiant, Long idDepartement) {
        Departement dep = departementRepository.findById(idDepartement)
                .orElseThrow(() -> new RuntimeException("Département introuvable !"));
        Etudiant etd = etudiantRepository.findById(idEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable !"));
        etd.setDepartement(dep);
        etudiantRepository.save(etd);
        return "Département affecté avec succès à l'étudiant " + etd.getNom();
    }
}
