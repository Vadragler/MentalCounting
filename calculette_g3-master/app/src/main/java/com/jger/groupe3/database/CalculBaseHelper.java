package com.jger.groupe3.database;

import android.content.Context;

public class CalculBaseHelper extends DataBaseHelper{

    public CalculBaseHelper(Context context) {
        super(context, "Calculs", 1);
    }

    @Override
    protected String getCreationSql() {
        String script = "CREATE TABLE IF NOT EXISTS historique (" +
                "id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                CalculDao.INDEX_PREMIER_ELEMENT + " INTEGER NOT NULL," +
                CalculDao.INDEX_DEUXIEME_ELEMENT + " INTEGER NOT NULL," +
                CalculDao.INDEX_SYMBOL + " VARCHAR(255) NOT NULL," +
                CalculDao.INDEX_RESULTAT + " INTEGER NOT NULL" +
                ")";
        return script;
    }

    @Override
    protected String getDeleteSql() {
        return null;
    }
}
