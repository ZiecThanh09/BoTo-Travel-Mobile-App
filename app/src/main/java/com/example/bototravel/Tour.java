package com.example.bototravel;

public class Tour {

    int id, entity, entity_total, status;
    String name;

    public Tour(int id, String name, int entity, int entity_total, int status) {
        this.id = id;
        this.name = name;
        this.entity = entity;
        this.entity_total = entity_total;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntity() {
        return entity;
    }

    public void setEntity(int entity) {
        this.entity = entity;
    }

    public int getEntity_total() {
        return entity_total;
    }

    public void setEntity_total(int entity_total) {
        this.entity_total = entity_total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
