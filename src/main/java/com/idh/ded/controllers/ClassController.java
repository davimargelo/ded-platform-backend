package com.idh.ded.controllers;

import com.idh.ded.domain.Class;
import com.idh.ded.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "classes")
public class ClassController {

    @Autowired
    ClassService classService;

    @GetMapping
    public ResponseEntity<List<Class>> getAll() {
        List<Class> allClasses = classService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(allClasses);
    }
    
    @GetMapping(value = "{classId}")
    public ResponseEntity<Class> findOne(@PathVariable("classId") String classId) {
        Class clas = classService.getOne(classId);
        return ResponseEntity.status(HttpStatus.OK).body(clas);
    }
}
