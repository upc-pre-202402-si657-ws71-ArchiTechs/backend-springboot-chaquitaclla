package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.aggregates.Product;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product product) {
        return new ProductResource(product.getId(), product.getSowingId(), product.getName(), product.getQuantity(), product.getAppliedDate(), product.getProductType());
    }
}