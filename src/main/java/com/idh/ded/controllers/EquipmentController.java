package com.idh.ded.controllers;

import com.idh.ded.domain.Equipment;
import com.idh.ded.services.EquipmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "equipments")
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @ApiOperation(value = "Get all Equipments By PAGE")
    @GetMapping(value = "page")
    public ResponseEntity<Page<Equipment>> getAllEquipmentsByPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10")Integer size,
            @RequestParam(value = "orderBy", defaultValue = "name")String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction) {
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.getAllEquipmentsByPage(page, size, orderBy, direction));
    }

    @ApiOperation(value = "Gets a Equipment by Id")
    @GetMapping(value = "/{equipId}")
    public ResponseEntity<Equipment> getOneEquipment(@PathVariable Integer equipId) {
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.getOne(equipId));
    }

    @Deprecated
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.getAll());
    }
}
