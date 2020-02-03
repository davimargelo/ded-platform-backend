package com.idh.ded.repositories;

import com.idh.ded.domain.DicePreset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DicePresetsRepository extends JpaRepository<DicePreset, Integer> {

    @Transactional(readOnly = true)
    Page<DicePreset> findDistinctByNameContainingIgnoreCase(String name, Pageable pageRequest);
}
