package gestionabscence.Repositories;

import gestionabscence.Entities.Absence;
import gestionabscence.Entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence,Long> {
    List<Absence> findByEtudiant(Etudiant etudiant);
    List<Absence> findByEtudiantIdEtudiant(Long idEtudiant);
}
