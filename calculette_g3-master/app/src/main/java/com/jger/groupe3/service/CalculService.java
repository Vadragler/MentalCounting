package com.jger.groupe3.service;

import com.jger.groupe3.database.CalculDao;
import com.jger.groupe3.entity.Calcul;

public class CalculService {
    private CalculDao calculDao;

    public CalculService(CalculDao calculDao) {
        this.calculDao = calculDao;
    }

    public long getCalculNumber(){
        return calculDao.count();
    }

    public void storeCalculInDatabase(Calcul calcul){
        calculDao.create(calcul);
    }
}
