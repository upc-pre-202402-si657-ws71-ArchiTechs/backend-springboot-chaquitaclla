package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.aggregates.Product;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.valueobjects.DateRange;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.entities.SowingControl;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.CropId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.PhenologicalPhase;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.ProfileId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Setter
@Entity
public class Sowing extends AuditableAbstractAggregateRoot<Sowing> {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Embedded
    private DateRange dateRange;

    @Embedded
    private ProfileId profileId;

    @Getter
    @NotNull
    private int areaLand;

    @Getter
    @NotNull
    private boolean status;

    @OneToMany(mappedBy = "sowing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SowingControl> sowingControls;

    @Embedded
    private CropId cropId;

    @OneToMany(mappedBy = "sowing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> sowingProducts;

    @Getter
    private PhenologicalPhase phenologicalPhase;


    public CropId getCropId() {
        return cropId;
    }

    public ProfileId getProfileId() {
        return profileId;
    }
    protected Sowing() {

    }
    public Sowing(CropId cropId,Integer areaLand){
        LocalDate startDate = LocalDate.now();
        this.dateRange = new DateRange(startDate, 6);
        this.areaLand = areaLand;
        this.cropId = cropId;
        this.phenologicalPhase = PhenologicalPhase.GERMINATION;
        this.profileId = new ProfileId(0L);
    }


    public void addSowingControl(SowingControl sowingControl) {
        if (sowingControls == null) {
            sowingControls = new ArrayList<>();
        }
        sowingControls.add(sowingControl);
        sowingControl.setSowing(this);
    }

    public void germinationPhase(){
        this.phenologicalPhase = PhenologicalPhase.GERMINATION;
    }
    public void harvestingPhase(){
        this.phenologicalPhase = PhenologicalPhase.HARVEST_READY;
    }
}