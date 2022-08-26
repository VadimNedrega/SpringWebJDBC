package com.vadim.spring.mvc.dao.kpac;

import com.vadim.spring.mvc.entity.KPAC;
import com.vadim.spring.mvc.entity.KPACSet;

import java.util.List;

public interface KPACDao {
    public void save(KPAC kpac);
    public void delete(int id);
    public List<KPAC> getAll();
    public KPAC get(int id);
    public void setSet(int kpacId, KPACSet kpacSet);
}
