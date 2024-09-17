package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.application.internal.queryservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.entities.Category;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllCategoriesQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetCategoryByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services.CategoryQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.infrastructure.persistence.jpa.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository categoryRepository;

    public CategoryQueryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> handle(GetAllCategoriesQuery query) {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> handle(GetCategoryByIdQuery query) {
        return categoryRepository.findById(query.categoryId());
    }
}
