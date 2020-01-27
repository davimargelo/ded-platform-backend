package com.idh.ded.services;

import com.idh.ded.domain.Races;
import com.idh.ded.repositories.RaceRepository;
import com.idh.ded.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceService {
    @Autowired
    RaceRepository racesRepository;

    public List<Races> getAll() {
        return racesRepository.findAll();
    }

    public Races getOne(String racesId) {
        return racesRepository.findById(racesId).orElseThrow(() -> new ObjectNotFoundException("404 - Not Found ID: " + racesId, new Throwable("Type: " + Races.class.getName())));
    }

}