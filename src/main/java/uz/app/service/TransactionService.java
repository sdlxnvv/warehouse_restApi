package uz.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.app.entity.Product;
import uz.app.entity.Transaction;
import uz.app.entity.Warehouse;
import uz.app.entity.enums.TransactionType;
import uz.app.repository.ProductRepository;
import uz.app.repository.TransactionRepository;
import uz.app.repository.WarehouseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public Transaction addTransaction(Transaction transaction) {
        // Проверка на продукт
        Product product = productRepository.findById(transaction.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + transaction.getProduct().getId()));

        // Проверяем тип и склады
        if (transaction.getType() == TransactionType.TRANSFER) {
            if (transaction.getWarehouseFrom() == null || transaction.getWarehouseTo() == null) {
                throw new RuntimeException("Transfer requires both source and destination warehouses!");
            }
        }

        // Проверка количества
        if (transaction.getQuantityChange() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        transaction.setProduct(product);
        transaction.setCreatedAt(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> list = transactionRepository.findAll();
        if (list.isEmpty()) {
            throw new RuntimeException("No transactions found!");
        }
        return list;
    }

    public Transaction getTransactionById(UUID id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }

    public Transaction updateTransaction(UUID id, Transaction updated) {
        Transaction existing = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));

        existing.setProduct(updated.getProduct());
        existing.setWarehouseFrom(updated.getWarehouseFrom());
        existing.setWarehouseTo(updated.getWarehouseTo());
        existing.setQuantityChange(updated.getQuantityChange());
        existing.setType(updated.getType());
        existing.setReference(updated.getReference());
        existing.setNotes(updated.getNotes());

        return transactionRepository.save(existing);
    }

    public void deleteTransaction(UUID id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found with id: " + id);
        }
        transactionRepository.deleteById(id);
    }

    public long getCount() {
        return transactionRepository.count();
    }

}
