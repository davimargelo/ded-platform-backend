package com.idh.ded.services;

import com.idh.ded.domain.Equipment;
import com.idh.ded.repositories.EquipmentRepository;
import com.idh.ded.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    EquipmentRepository repo;

    public List<Equipment> getAll() {
        return repo.findAll();
    }

    public Equipment getOne(Integer equipmentId) {
        return repo.findById(equipmentId).orElseThrow(() -> new ObjectNotFoundException("404 - Not Found ID: " + equipmentId, new Throwable("Type: " + Equipment.class.getName())));
    }

    public Page<Equipment> getAllEquipmentsByPage(String name, Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return repo.findDistinctByNameContainingIgnoreCase(name, pageRequest);
    }

}
