package uz.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "warehouses") // ✅ во множественном числе — стандартно для таблиц
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private String code;
    private String address;

    @Column(columnDefinition = "TEXT")
    private String metadata;

    @OneToMany(mappedBy = "warehouseFrom")
    @JsonIgnore
    private List<Transaction> transactionsFrom;

    @OneToMany(mappedBy = "warehouseTo")
    @JsonIgnore
    private List<Transaction> transactionsTo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    // --- Lifecycle Hooks ---
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}