package uz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.entity.Transaction;
import uz.app.entity.enums.TransactionType;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByType(TransactionType type);
    List<Transaction> findByProductId(UUID productId);
    List<Transaction> findByCreatedById(UUID userId);
}
