package com.idh.ded.repositories;

import com.idh.ded.domain.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, String> {
}
