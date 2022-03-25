package com.example.mentalcounting.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;

import com.example.mentalcounting.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Button boutonCalculer = findViewById(R.id.bouton_calculer);
        Button boutonStatistique = findViewById(R.id.bouton_statistique);
        Button boutonCredits = findViewById(R.id.bouton_credit);
        boutonCalculer.setOnClickListener(view -> lanceActiviteCalculer());
        boutonStatistique.setOnClickListener(view -> lanceActiviteStatistique());
        boutonCredits.setOnClickListener(view -> lanceActiviteCredit());

    }

    private void lanceActiviteStatistique() {
        Intent i = new Intent(this, StatistiqueActivity.class);
        startActivity(i);
    }

    private void lanceActiviteCalculer() {
        Intent i = new Intent(this, CalculActivity.class);
        startActivity(i);
    }

    private void lanceActiviteCredit() {
        Intent i = new Intent(this, CreditActivity.class);
        startActivity(i);
    }
}