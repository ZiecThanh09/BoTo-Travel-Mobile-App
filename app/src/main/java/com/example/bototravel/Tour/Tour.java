package com.example.bototravel.Tour;

public class Tour {

    int id, entity, entity_total, status, startDate, startMonth, startYear, endDate, endMonth, endYear;
    String name, start, end;

    public Tour(int id, String name, int entity, int entity_total, int status, int startMonth) {
        this.id = id;
        this.name = name;
        this.entity = entity;
        this.entity_total = entity_total;
        this.status = status;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    public Tour(int id, String name, String start, String end, int entity, int entity_total, int status) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }
}
