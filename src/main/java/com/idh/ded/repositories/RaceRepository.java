package com.idh.ded.repositories;

import com.idh.ded.domain.Races;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Races, String> {
}
