package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Care;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Disease;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.DiseaseRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.PestRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.CareRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Getter
public class Crop extends AuditableAbstractAggregateRoot<Crop> {

    @Getter
    @NotNull
    @Size(max = 500)
    private String Description;

    @Getter
    @NotNull
    @Size(max = 30)
    private String Name;

    @Getter
    @NotNull
    private String ImageUrl;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "crop_diseases",
            joinColumns = @JoinColumn(name = "crop_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id"))
    private List<Disease> diseases;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "crop_pests",
            joinColumns = @JoinColumn(name = "crop_id"),
            inverseJoinColumns = @JoinColumn(name = "pest_id")
    )
    private List<Pest> pests;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "crop_cares",
            joinColumns = @JoinColumn(name = "crop_id"),
            inverseJoinColumns = @JoinColumn(name = "care_id"))
    private List<Care> cares = new ArrayList<>();
    public Crop(){
        diseases = new ArrayList<>();
        pests = new ArrayList<>();
    }


    public Crop(String name, String description, String imageUrl, List<Disease> diseases, List<Pest> pests, List<Care> cares) {
        this();
        this.Name = name;
        this.Description = description;
        this.ImageUrl = imageUrl;
        this.diseases = diseases != null ? diseases : new ArrayList<>();
        this.pests = pests != null ? pests : new ArrayList<>();
        this.cares = cares != null ? cares : new ArrayList<>();
    }

    public Crop addDisease(Disease disease) {
        this.diseases.add(disease);
        return this;
    }

    public Crop addPest(Pest pest) {
        this.pests.add(pest);
        return this;
    }

    public Crop addCare(Care care) {
        this.cares.add(care);
        return this;
    }

    public Crop addDiseases(List<Disease> diseases) {
        this.diseases.addAll(diseases);
        return this;
    }

    public Crop addPests(List<Pest> pests) {
        this.pests.addAll(pests);
        return this;
    }

    public Crop setName(String name) {
        this.Name = name;
        return this;
    }

    public Crop setDescription(String description) {
        this.Description = description;
        return this;
    }

    public List<Long> getDiseaseIds() {
          return diseases.stream().map(Disease::getId).collect(Collectors.toList());
    }

    public List<Long> getPestIds() {
        return pests.stream().map(Pest::getId).collect(Collectors.toList());
    }

    public List<Long> getCareIds() {
        return cares.stream().map(Care::getId).collect(Collectors.toList());
    }

}