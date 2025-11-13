package uz.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.app.entity.Category;
import uz.app.service.CategoryService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Tag(name = "Category Controller", description = "CRUD + search + pagination for categories")
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // ➕ CREATE
    @Operation(summary = "Create a new category")
    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.addCategory(category);
        return ResponseEntity.status(201).body(savedCategory);
    }

    // 📋 READ ALL
    @Operation(summary = "Get all categories")
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // 🔍 SEARCH by name
    @Operation(summary = "Search categories by name")
    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(categoryService.searchByName(name));
    }

    // 🔎 READ by ID
    @Operation(summary = "Get category by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID id) {
        Category category = categoryService.getCategoryById(id);
        return category != null
                ? ResponseEntity.ok(category)
                : ResponseEntity.notFound().build();
    }

    // ✏️ UPDATE
    @Operation(summary = "Update category by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable UUID id, @RequestBody Category updatedCategory) {
        Category category = categoryService.updateCategory(id, updatedCategory);
        return ResponseEntity.ok(category);
    }

    // ❌ DELETE (soft delete можно позже внедрить)
    @Operation(summary = "Delete category by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get total count of products")
    @GetMapping("/count")
    public Map<String, Long> countProducts() {
        return Map.of("count", categoryService.getCount());
    }
}
