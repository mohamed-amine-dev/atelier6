package gestionabscence.DTOs;

import java.time.LocalDate;

public record AbsenceUpdate(
        String module,
        LocalDate dateAbsence
) {
}
