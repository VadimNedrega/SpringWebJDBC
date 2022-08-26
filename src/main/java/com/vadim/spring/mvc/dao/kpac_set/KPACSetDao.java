package com.vadim.spring.mvc.dao.kpac_set;

import com.vadim.spring.mvc.entity.KPACSet;

import java.util.List;

public interface KPACSetDao {
    public void save(KPACSet kpacSet);
    public void delete(int id);
    public List<KPACSet> getAll();
    public KPACSet get(int id);
}
