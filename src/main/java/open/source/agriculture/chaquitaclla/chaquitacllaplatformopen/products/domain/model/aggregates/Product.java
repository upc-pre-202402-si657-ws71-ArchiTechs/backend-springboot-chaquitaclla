package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.commands.CreateProductCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.valueobjects.ProductType;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates.Sowing;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long quantity;

    @NotNull
    private ProductType productType;

    @NotNull
    private LocalDate appliedDate;

    @ManyToOne
    @JoinColumn(name = "sowing_id", insertable = false, updatable = false)
    private Sowing sowing;

    @NotNull
    @Column(name = "sowing_id")
    private Long sowingId;

    public Product(CreateProductCommand command) {
        this.sowingId = command.sowingId();
        this.name = command.name();
        this.quantity = command.quantity();
        this.productType = command.productType();
        this.appliedDate = LocalDate.now();
    }
    public Product(){

    }
}