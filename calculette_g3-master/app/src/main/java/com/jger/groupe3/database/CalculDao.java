package com.jger.groupe3.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.jger.groupe3.entity.Calcul;


public class CalculDao extends BaseDao<Calcul>{
    static String INDEX_PREMIER_ELEMENT = "premierElement";
    static String INDEX_DEUXIEME_ELEMENT= "deuxiemeElement";
    static String INDEX_SYMBOL = "symbol";
    static String INDEX_RESULTAT = "resultat";
    public CalculDao(DataBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return "historique";
    }

    @Override
    protected void putValues(ContentValues values, Calcul entity) {
        values.put(INDEX_PREMIER_ELEMENT,entity.getPremierElement());
        values.put(INDEX_DEUXIEME_ELEMENT,entity.getDeuxiemeElement());
        values.put(INDEX_SYMBOL,entity.getSymbol());
        values.put(INDEX_RESULTAT,entity.getResultat());
    }

    @Override
    protected Calcul getEntity(Cursor cursor) {
        cursor.moveToFirst();
        Calcul calcul = new Calcul();
        Integer indexPremierElement = cursor.getColumnIndex(INDEX_PREMIER_ELEMENT);
        calcul.setPremierElement(cursor.getInt(indexPremierElement));
        Integer indexDeuxiemeElement = cursor.getColumnIndex(INDEX_DEUXIEME_ELEMENT);
        calcul.setDeuxiemeElement(cursor.getInt(indexDeuxiemeElement));
        Integer indexSymbol = cursor.getColumnIndex(INDEX_SYMBOL);
        calcul.setSymbol(cursor.getString(indexSymbol));
        Integer indexResultat = cursor.getColumnIndex(INDEX_RESULTAT);
        calcul.setResultat(cursor.getDouble(indexResultat));
        return calcul;
    }
}
