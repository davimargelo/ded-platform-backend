package com.idh.ded.services;

import com.idh.ded.domain.Class;
import com.idh.ded.repositories.ClassRepository;
import com.idh.ded.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    @Autowired
    ClassRepository classRepository;

    public List<Class> getAll() {
        return classRepository.findAll();
    }

    public Class getOne(String classId) {
        return classRepository.findById(classId).orElseThrow(() -> new ObjectNotFoundException("404 - Not Found ID: " + classId, new Throwable("Type: " + Class.class.getName())));
    }

}
