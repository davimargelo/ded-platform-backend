package com.idh.ded.repositories;

import com.idh.ded.domain.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    @Transactional(readOnly = true)
    Page<Equipment> findDistinctByNameContainingIgnoreCase(String name, Pageable pageRequest);
}
