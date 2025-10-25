package uz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.app.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);

    List<Product> findByCategoryId(UUID categoryId);

    List<Product> findByPriceBetween(BigDecimal price, BigDecimal price2);

    boolean existsByName(String name);

    List<Product> findDistinctByTransactionsWarehouseFromId(UUID warehouseId);

    // Или Вариант B: явный запрос
    @Query("SELECT DISTINCT p FROM Product p JOIN p.transactions t WHERE t.warehouseFrom.id = :warehouseId")
    List<Product> findProductsByWarehouseId(@Param("warehouseId") UUID warehouseId);

//    List<Product> findProductByWarehouseId(UUID warehouseId);
}
