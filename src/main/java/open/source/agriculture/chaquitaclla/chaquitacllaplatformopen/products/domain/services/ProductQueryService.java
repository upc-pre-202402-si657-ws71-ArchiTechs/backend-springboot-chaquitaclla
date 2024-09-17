package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.services;


import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.aggregates.Product;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.queries.GetProductByIdQuery;

import java.util.Optional;

public interface ProductQueryService {
    /*List<Product> handle(GetProductsBySowingIdQuery query);*/
    Optional<Product> handle(GetProductByIdQuery query);
}
