package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.commands.CreateProductCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.commands.DeleteProductCommand;

public interface ProductCommandService {
    Long handle(CreateProductCommand command);
    void handle(DeleteProductCommand command);
}
