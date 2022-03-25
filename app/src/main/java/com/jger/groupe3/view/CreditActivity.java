package com.jger.groupe3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jger.groupe3.R;

import java.util.Locale;

public class CreditActivity extends AppCompatActivity {
    Locale frLocale = new Locale("fr", "FR");
    Locale defaultLocale = Locale.getDefault();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        TextView textViewCredit = findViewById(R.id.textViewCredit);
        String langage;
        String Developpeur;
        if (frLocale.equals(defaultLocale)){
            Developpeur="Développeur: ";
            langage="Langage pris en charge:\nAnglais\nFrançais";
        }else{
            Developpeur="Developer: ";
            langage="Supported language:\nEnglish\nFrench";
        }
        textViewCredit.setText(Developpeur+"\nCédric Gravelard\nTom Lefevre\n\n"+langage);
    }



}