package gestionabscence.Services;

import gestionabscence.Entities.Departement;
import gestionabscence.Repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public String createDepartement(Departement departement) {
        departementRepository.save(departement);
        return "Département enregistré avec succès";
    }

    public List<Departement> findAll() {
        return departementRepository.findAll();
    }

    public Departement findOne(Long id) {
        return departementRepository.findById(id).orElse(null);
    }

    public String updateDepartement(Long id, Departement departement) {
        Departement dep = departementRepository.findById(id).orElse(null);
        if(dep != null){
            dep.setNomDepartement(departement.getNomDepartement());
            departementRepository.save(dep);
            return "Succès";
        }
        return "Échec";
    }

    public String deleteDepartement(Long id) {
        Departement dep = departementRepository.findById(id).orElse(null);
        if(dep != null){
            departementRepository.deleteById(id);
            return "Succès";
        }
        return "Échec";
    }
}
