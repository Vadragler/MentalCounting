package com.jger.MentalCounting.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.jger.MentalCounting.entity.Calcul;


public class CalculDao extends BaseDao<Calcul> {
    static String INDEX_SCORE = "Score";

    public CalculDao(DataBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return "statistique";
    }

    @Override
    protected void putValues(ContentValues values, Calcul entity) {
        values.put(INDEX_SCORE, entity.getScore());
    }

    @Override
    protected Calcul getEntity(Cursor cursor) {
        cursor.moveToFirst();
        Calcul calcul = new Calcul();
        Integer indexScore = cursor.getColumnIndex(INDEX_SCORE);
        calcul.setScore(cursor.getInt(indexScore));
        return calcul;
    }
}
