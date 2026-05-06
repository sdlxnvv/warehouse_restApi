package uz.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.app.entity.Transaction;
import uz.app.service.TransactionService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Tag(name = "Transaction Controller", description = "CRUD operations for managing product transactions")
@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // ➕ Create
    @Operation(summary = "Create a new transaction")
    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.addTransaction(transaction);
        return ResponseEntity.status(201).body(savedTransaction); // ✅ 201 Created
    }

    // 📋 Read All
    @Operation(summary = "Get all transactions")
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    // 🔍 Read By Id
    @Operation(summary = "Get transaction by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable UUID id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return transaction != null
                ? ResponseEntity.ok(transaction)
                : ResponseEntity.notFound().build(); // ✅ 404 if not found
    }

    // ✏️ Update
    @Operation(summary = "Update transaction by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable UUID id,
                                                         @RequestBody Transaction updatedTransaction) {
        Transaction transaction = transactionService.updateTransaction(id, updatedTransaction);
        return ResponseEntity.ok(transaction);
    }

    // ❌ Delete
    @Operation(summary = "Delete transaction by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build(); // ✅ 204 No Content
    }

    @Operation(summary = "Get total count of transactions")
    @GetMapping("/count")
    public Map<String, Long> countTransactions() {
        return Map.of("count", transactionService.getCount());
    }

}
