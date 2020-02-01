package com.idh.ded.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Spell {

    @Id
    private String id;
    private String name;
    @Lob
    private String effectDescription;
    private String url;

    public Spell(String id, String name, String effectDescription, String url) {
        this.id = id;
        this.name = name;
        this.effectDescription = effectDescription;
        this.url = url;
    }

    public Spell() {
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

    public String getEffect() {
        return effectDescription;
    }

    public void setEffect(String effect) {
        this.effectDescription = effect;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
