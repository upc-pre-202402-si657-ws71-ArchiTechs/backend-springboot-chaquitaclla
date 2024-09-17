package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.valueobjects.ProductType;

public record CreateProductCommand(Long sowingId,
                                   String name,
                                   Long quantity,
                                   ProductType productType) {
}