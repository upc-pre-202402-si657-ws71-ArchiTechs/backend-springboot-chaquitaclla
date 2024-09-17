package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.application.internal.queryservices;


import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.aggregates.Product;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.queries.GetProductByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.services.ProductQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

/*
    @Override
    public List<Product> handle(GetProductsBySowingIdQuery query) {
        return this.productRepository.findAll();
    }
*/
    @Override
    public Optional<Product> handle(GetProductByIdQuery query) {
        return this.productRepository.findById(query.productId());
    }

}
