package com.idh.ded.repositories;

import com.idh.ded.domain.DicePreset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DicePresetsRepository extends JpaRepository<DicePreset, String> {

}
