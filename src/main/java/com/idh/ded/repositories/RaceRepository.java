package com.idh.ded.repositories;

import com.idh.ded.DTOs.Races;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Races, String> {
}
