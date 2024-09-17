package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.interfaces.rest.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.valueobjects.ProductType;

import java.time.LocalDate;

public record ProductResource(Long id, Long sowingId,String name, Long quantity, LocalDate appliedDate,ProductType productType) {
}
