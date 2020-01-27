package com.idh.ded.repositories;

import com.idh.ded.domain.Dice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiceRollRepository extends JpaRepository<Dice, Integer> {
}
