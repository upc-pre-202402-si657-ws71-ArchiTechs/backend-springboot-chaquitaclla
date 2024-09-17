package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.entities.Category;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllCategoriesQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetCategoryByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CategoryQueryService {
    List<Category> handle(GetAllCategoriesQuery query);
    Optional<Category> handle(GetCategoryByIdQuery query);
}
