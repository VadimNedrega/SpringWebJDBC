package com.vadim.spring.mvc.entity;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class KPAC {
    private int id;
    private String title;
    private String description;
    private String creationDate;
    private String filter;

    private Integer kpacSet = null;

    public KPAC() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Integer getKpacSet() {
        return kpacSet;
    }

    public void setKpacSet(Integer kpacSet) {
        this.kpacSet = kpacSet;
    }


    @Override
    public String toString(){
        return "{ID="+id+",Title="+title+",Description="+description+",Creation date="+creationDate+",KPACSet="+kpacSet+"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KPAC kpac = (KPAC) o;
        return Objects.equals(title, kpac.title) && Objects.equals(description, kpac.description) && Objects.equals(creationDate, kpac.creationDate) && Objects.equals(kpacSet, kpac.kpacSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, creationDate, kpacSet);
    }
}
