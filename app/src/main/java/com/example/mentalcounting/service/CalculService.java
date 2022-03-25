package com.example.mentalcounting.service;

import com.example.mentalcounting.database.CalculDao;
import com.example.mentalcounting.entity.Calcul;

public class CalculService {

    private final CalculDao calculDao;

    public CalculService(CalculDao calculDao) {
        this.calculDao = calculDao;
    }

    public long getCalculNumber() {
        return calculDao.count();
    }

    public String gettop() {
        return calculDao.top();
    }

    public void storeScoreInDatabase(Calcul calcul) {
        calculDao.create(calcul);
    }
}
