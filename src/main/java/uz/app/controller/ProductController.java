package uz.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.app.entity.Product;
import uz.app.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Tag(name = "Product Controller", description = "CRUD operations for managing products")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ➕ CREATE
    @Operation(summary = "Create a new product")
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.status(201).body(savedProduct); // ✅ 201 Created
    }

    // 📋 READ ALL
    @Operation(summary = "Get all products")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // 🔍 READ BY ID
    @Operation(summary = "Get product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        Product product = productService.getProductById(id);
        return product != null
                ? ResponseEntity.ok(product)
                : ResponseEntity.notFound().build(); // ✅ 404 if not found
    }

    // ✏️ UPDATE
    @Operation(summary = "Update product by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody Product updatedProduct) {
        Product product = productService.updateProduct(id, updatedProduct);
        return ResponseEntity.ok(product);
    }

    // ❌ DELETE
    @Operation(summary = "Delete product by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build(); // ✅ 204 No Content
    }
//
//    @GetMapping("/count")
//    public Map<String, Long> countProducts() {
//        return Map.of("count", productRepository.count());
//    }

}
