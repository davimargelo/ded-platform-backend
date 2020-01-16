package com.idh.ded.repositories;

import com.idh.ded.DTOs.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellRepository extends JpaRepository<Spell, String> {
}
