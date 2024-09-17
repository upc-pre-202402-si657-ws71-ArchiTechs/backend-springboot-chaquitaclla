package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.interfaces.rest.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.valueobjects.ProductType;

public record CreateProductResource(Long sowingId,String name, Long quantity, ProductType productType) {
}
