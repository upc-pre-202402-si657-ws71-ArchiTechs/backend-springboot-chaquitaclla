package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.entities.AuditableModel;

import java.time.LocalDate;

@Getter
@Entity
public class Care extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    @NotNull
    private LocalDate careDate;

    public Care(String description, LocalDate careDate) {
        this.description = description;
        this.careDate = careDate;
    }
    public Care()
    {
    }
}