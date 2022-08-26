package com.vadim.spring.mvc.entity;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class KPACSet {
    private int id;
    private String title;
    private Integer kpacId;
    private Set<KPAC> kpacSet;

    public Set<KPAC> getKpacSet() {
        return kpacSet;
    }

    public void setKpacSet(Set<KPAC> kpacSet) {
        this.kpacSet = kpacSet;
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

    public Integer getKpacId() {
        return kpacId;
    }

    public void setKpacId(Integer kpacId) {
        this.kpacId = kpacId;
    }

    @Override
    public String toString(){
        return "{ID="+id+",Title="+title+",K-PAC-ID="+kpacId+"}";
    }
}
