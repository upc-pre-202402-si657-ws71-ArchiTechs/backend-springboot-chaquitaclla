package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest;


import io.swagger.v3.oas.annotations.tags.Tag;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.DeleteCategoryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllCategoriesQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetCategoryByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services.CategoryCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services.CategoryQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.CategoryResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.CreateCategoryResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.UpdateCategoryResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform.CategoryResourceFromEntityAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform.CreateCategoryCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform.UpdateCategoryCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/forum/categories")
@Tag(name = "Categories", description = "Categories Management Endpoints")
public class CategoriesController {
    private final CategoryCommandService categoryCommandService;
    private final CategoryQueryService categoryQueryService;

    public CategoriesController(CategoryCommandService categoryCommandService, CategoryQueryService categoryQueryService) {
        this.categoryCommandService = categoryCommandService;
        this.categoryQueryService = categoryQueryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResource> createCategory(@RequestBody CreateCategoryResource resource){
        var createCategoryCommand = CreateCategoryCommandFromResourceAssembler.toCommandFromResource(resource);
        System.out.println(createCategoryCommand);
        var categoryId = categoryCommandService.handle(createCategoryCommand);
        if(categoryId == 0L) return ResponseEntity.badRequest().build();
        var getCategoryByIdQuery = new GetCategoryByIdQuery(categoryId);
        var category = categoryQueryService.handle(getCategoryByIdQuery);
        if(category.isEmpty()) return ResponseEntity.badRequest().build();
        var categoryResource =  CategoryResourceFromEntityAssembler.toResourceFromEntity(category.get());
        return new ResponseEntity<>(categoryResource, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResource> updateCategory(@PathVariable Long categoryId, @RequestBody UpdateCategoryResource resource) {
        var updateCategoryCommand = UpdateCategoryCommandFromResourceAssembler.toCommandFromResource(categoryId, resource);
        var updatedCategory = categoryCommandService.handle(updateCategoryCommand);
        if(updatedCategory.isEmpty()) return ResponseEntity.badRequest().build();
        var categoryResource = CategoryResourceFromEntityAssembler.toResourceFromEntity(updatedCategory.get());
        return ResponseEntity.ok(categoryResource);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
        var deleteCategoryCommand = new DeleteCategoryCommand(categoryId);
        categoryCommandService.handle(deleteCategoryCommand);
        return ResponseEntity.ok("Course with given id successfully deleted");
    }


    @GetMapping
    public ResponseEntity<List<CategoryResource>> getAllCategories(){
        var getAllCategoriesQuery = new GetAllCategoriesQuery();
        var categories = categoryQueryService.handle(getAllCategoriesQuery);
        if(categories.isEmpty()) return ResponseEntity.notFound().build();
        var categoryResources = categories.stream().map(CategoryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(categoryResources);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResource> getCategoryById(@PathVariable Long categoryId){
        var getCategoryById = new GetCategoryByIdQuery(categoryId);
        var category = categoryQueryService.handle(getCategoryById);
        if(category.isEmpty()) return ResponseEntity.notFound().build();
        var categoryResource = CategoryResourceFromEntityAssembler.toResourceFromEntity(category.get());
        return ResponseEntity.ok(categoryResource);
    }

}
