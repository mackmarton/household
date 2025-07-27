package com.mackmarton.household.repositories;

import com.mackmarton.household.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
