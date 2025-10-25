package uz.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.app.entity.Warehouse;
import uz.app.service.WarehouseService;

import java.util.List;
import java.util.UUID;

@Tag(name = "Warehouse Controller", description = "CRUD operations for managing warehouses")
@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    // ➕ CREATE
    @Operation(summary = "Create a new warehouse")
    @PostMapping
    public ResponseEntity<Warehouse> addWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse savedWarehouse = warehouseService.addWarehouse(warehouse);
        return ResponseEntity.status(201).body(savedWarehouse);
    }

    // 📋 READ ALL
    @Operation(summary = "Get all warehouses")
    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    // 🔍 READ BY ID
    @Operation(summary = "Get warehouse by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable UUID id) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        return warehouse != null
                ? ResponseEntity.ok(warehouse)
                : ResponseEntity.notFound().build();
    }

    // ✏️ UPDATE
    @Operation(summary = "Update warehouse by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable UUID id, @RequestBody Warehouse updatedWarehouse) {
        Warehouse warehouse = warehouseService.updateWarehouse(id, updatedWarehouse);
        return ResponseEntity.ok(warehouse);
    }

    // ❌ DELETE
    @Operation(summary = "Delete warehouse by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable UUID id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }
}
