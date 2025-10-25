package uz.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.app.entity.Warehouse;
import uz.app.repository.WarehouseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    // Добавление нового склада
    public Warehouse addWarehouse(Warehouse warehouse) {
        if (warehouseRepository.existsByName(warehouse.getName())) {
            throw new RuntimeException("Warehouse already exists with name: " + warehouse.getName());
        }
        warehouse.setCreatedAt(LocalDateTime.now());
        return warehouseRepository.save(warehouse);
    }

    // Получение всех складов
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    // Получение склада по id
    public Warehouse getWarehouseById(UUID id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + id));
    }

    // Обновление склада
    public Warehouse updateWarehouse(UUID id, Warehouse updatedWarehouse) {
        Warehouse existing = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + id));

        existing.setName(updatedWarehouse.getName());
        existing.setCode(updatedWarehouse.getCode());
        existing.setAddress(updatedWarehouse.getAddress());
        existing.setMetadata(updatedWarehouse.getMetadata());
        existing.setUpdatedAt(LocalDateTime.now());

        return warehouseRepository.save(existing);
    }

    // Удаление склада
    public void deleteWarehouse(UUID id) {
        Warehouse existing = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + id));
        warehouseRepository.delete(existing);
    }
}
