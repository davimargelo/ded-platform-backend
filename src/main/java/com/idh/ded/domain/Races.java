package com.idh.ded.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Races {

    @Id
    private String id;
    private String name;
    private String racesDescription;
    private String url;

    public Races(String id, String name, String racesDescription, String url) {
        this.id = id;
        this.name = name;
        this.racesDescription = racesDescription;
        this.url = url;
    }

    public Races() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRaces() {
        return racesDescription;
    }

    public void setRaces(String races) {
        this.racesDescription = races;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
