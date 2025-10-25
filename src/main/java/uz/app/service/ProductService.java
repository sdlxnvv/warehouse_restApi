package uz.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.app.entity.Category;
import uz.app.entity.Product;
import uz.app.repository.CategoryRepository;
import uz.app.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // ➕ CREATE
    public Product addProduct(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new RuntimeException("Product already exists!");
        }

        // Проверка на существование категории
        if (product.getCategory() != null) {
            UUID categoryId = product.getCategory().getId();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
            product.setCategory(category);
        }

        return productRepository.save(product);
    }

    // 📋 READ ALL
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 🔍 READ BY ID
    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // ✏️ UPDATE
    public Product updateProduct(UUID id, Product updatedProduct) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());

        if (updatedProduct.getCategory() != null) {
            UUID categoryId = updatedProduct.getCategory().getId();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
            existing.setCategory(category);
        }

        return productRepository.save(existing);
    }

    // ❌ DELETE
    public void deleteProduct(UUID id) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        productRepository.delete(existing);
    }

    // 🔎 FIND BY WAREHOUSE (если есть связь)
    public List<Product> getAllProductsByWarehouseId(UUID warehouseId) {
        List<Product> products = productRepository.findProductsByWarehouseId(warehouseId);
        if (products.isEmpty()) {
            throw new RuntimeException("No products found in warehouse with id: " + warehouseId);
        }
        return products;
    }
}
