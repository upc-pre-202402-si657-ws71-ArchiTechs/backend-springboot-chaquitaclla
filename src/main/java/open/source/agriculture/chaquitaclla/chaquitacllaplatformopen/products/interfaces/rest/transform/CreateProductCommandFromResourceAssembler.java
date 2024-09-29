package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.commands.CreateProductCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(Long sowingId, CreateProductResource resource) {
        return new CreateProductCommand(
                sowingId,
                resource.name(),
                resource.quantity(),
                resource.productType());
    }
}
