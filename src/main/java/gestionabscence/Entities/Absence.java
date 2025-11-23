package gestionabscence.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "absence")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAbsence")
    private Long idAbsence;

    @Column(nullable = false)
    private String module;

    @Column(nullable = false)
    private LocalDate dateAbsence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etudiant", nullable = false)
    private Etudiant etudiant;

}
