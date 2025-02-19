package com.inventory.repository;

import com.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    boolean existsBySkuCodeAndQuantityGreaterThanEqual(String skuCode, Integer quantityIsGreaterThan);
}
