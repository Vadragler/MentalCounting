package com.jger.MentalCounting.database;

import android.content.Context;

public class CalculBaseHelper extends DataBaseHelper {

    public CalculBaseHelper(Context context) {
        super(context, "Score", 1);
    }

    @Override
    protected String getCreationSql() {
        return "CREATE TABLE IF NOT EXISTS statistique (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                CalculDao.INDEX_SCORE + " INTEGER)";
    }

    @Override
    protected String getDeleteSql() {
        return null;
    }
}
