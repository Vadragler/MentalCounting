package com.example.mentalcounting.view;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mentalcounting.R;
import com.example.mentalcounting.database.CalculBaseHelper;
import com.example.mentalcounting.database.CalculDao;
import com.example.mentalcounting.entity.Calcul;
import com.example.mentalcounting.service.CalculService;

import java.util.Locale;
import java.util.Random;

public class CalculActivity extends AppCompatActivity {
    private Integer Nombre = 0;
    private Integer Dixieme = 0;
    private Integer Centieme = 0;
    private TextView textViewCalcul;
    private TextView textViewTrouve;
    private TextView textViewScore;
    private TextView textViewVies;
    private final Integer BORNE_HAUTE = 99999;
    private final Integer BORNE_HAUTE2 = 99;
    private Integer NbDeVirgule = 0;
    private String Virgule;
    private CalculService calculService;
    private Double TrouveFinale;
    private Integer Vies = 3;
    private Boolean Virguleboolean = false;
    private Integer Score = 0;
    private Boolean Negative = false;
    Locale frLocale = new Locale("fr", "FR");
    Locale defaultLocale = Locale.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        calculService = new CalculService(new CalculDao(new CalculBaseHelper(this)));
        textViewCalcul = findViewById(R.id.textViewCalcul);
        textViewTrouve = findViewById(R.id.textViewTrouve);
        textViewScore = findViewById(R.id.textViewScore);
        textViewVies = findViewById(R.id.textViewVies);
        TextViewScore();
        TextViewVies();
        Button bouton1 = findViewById(R.id.bouton_1);
        bouton1.setOnClickListener(view -> ajouteValeur(1));
        Button bouton2 = findViewById(R.id.bouton_statistique);
        bouton2.setOnClickListener(view -> ajouteValeur(2));
        Button bouton3 = findViewById(R.id.bouton_3);
        bouton3.setOnClickListener(view -> ajouteValeur(3));
        Button bouton4 = findViewById(R.id.bouton_4);
        bouton4.setOnClickListener(view -> ajouteValeur(4));
        Button bouton5 = findViewById(R.id.bouton_5);
        bouton5.setOnClickListener(view -> ajouteValeur(5));
        Button bouton6 = findViewById(R.id.bouton_6);
        bouton6.setOnClickListener(view -> ajouteValeur(6));
        Button bouton7 = findViewById(R.id.bouton_7);
        bouton7.setOnClickListener(view -> ajouteValeur(7));
        Button bouton8 = findViewById(R.id.bouton_8);
        bouton8.setOnClickListener(view -> ajouteValeur(8));
        Button bouton9 = findViewById(R.id.bouton_9);
        bouton9.setOnClickListener(view -> ajouteValeur(9));
        Button bouton0 = findViewById(R.id.bouton_0);
        bouton0.setOnClickListener(view -> ajouteValeur(0));
        Button boutonC = findViewById(R.id.bouton_C);
        boutonC.setOnClickListener(view -> negativeValeur());
        Button boutonVirgule = findViewById(R.id.bouton_virgule);
        boutonVirgule.setOnClickListener(view -> ajouterVirgule());
        Button boutonValider = findViewById(R.id.bouton_Valider);
        boutonValider.setOnClickListener(view -> calculResultat());
        TextViewTrouve();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        MenuItem toolbarRetour = menu.findItem(R.id.toolbarRetour);
        MenuItem toolbarVider = menu.findItem(R.id.toolbarVider);
        toolbarRetour.setOnMenuItemClickListener(menuItem -> finir());
        toolbarVider.setOnMenuItemClickListener(menuItem -> videTextView());
        return true;
    }

    private void ajouterVirgule() {
        Virguleboolean = true;


        if (defaultLocale.equals(frLocale)) {
            Virgule = ",";
        } else {
            Virgule = ".";
        }
        NbDeVirgule++;
        majTextView();

    }

    private void negativeValeur() {

        Negative = Negative == false;
        majTextView();
    }

    private void ajouteValeur(int valeur) {
        if (Virguleboolean == false) {
            if (10 * Nombre + valeur > BORNE_HAUTE) {
                Toast.makeText(this, getString(R.string.ERREUR_TROP_GRAND), Toast.LENGTH_LONG).show();
            } else {
                Nombre = 10 * Nombre + valeur;
            }
        } else if (NbDeVirgule == 1) {
            if (10 * Dixieme + valeur > BORNE_HAUTE2) {
                Toast.makeText(this, getString(R.string.ERREUR_TROP_GRAND), Toast.LENGTH_LONG).show();
            } else if (valeur == 0) {
                Dixieme = 0;
                NbDeVirgule++;
            } else {
                Dixieme = 10 * Dixieme + valeur;
            }
        } else if (NbDeVirgule == 2) {
            Centieme = Centieme + valeur;

        }

        majTextView();
    }

    private void majTextView() {
        String textAAfficher = "";
        if (Virguleboolean == false) {
            textAAfficher = Nombre.toString();
        } else if (NbDeVirgule == 1) {
            if (Dixieme == 0) {
                textAAfficher = Nombre + Virgule;
            } else {
                textAAfficher = Nombre + Virgule + Dixieme;
            }
            System.out.println(textAAfficher);


        } else if (NbDeVirgule == 2) {
            if (Centieme == 0) {
                textAAfficher = Nombre + Virgule + Dixieme;
            } else {
                textAAfficher = Nombre + Virgule + Dixieme + Centieme;
            }
        }
        if (Negative == true) {
            textAAfficher = "-" + textAAfficher;
        }
        textViewCalcul.setText(textAAfficher);
    }

    private void TextViewTrouve() {
        int random1 = 0;
        int random2 = 0;
        int symbolint = new Random().nextInt((4 - 1) + 1) + 1;
        double trouve = 0;

        String symbol = "";
        switch (symbolint) {
            case 1:
                symbol = "+";
                random1 = new Random().nextInt((99 - -99) + 1) + -99;
                random2 = new Random().nextInt((99 - 0) + 1) + 0;
                trouve = (double) random1 + random2;

                break;
            case 2:
                symbol = "-";
                random1 = new Random().nextInt((99 - -99) + 1) + -99;
                random2 = new Random().nextInt((99 - 0) + 1) + 0;
                trouve = (double) random1 - random2;

                break;
            case 3:
                symbol = "*";
                random1 = new Random().nextInt((99 - -99) + 1) + -99;
                random2 = new Random().nextInt((99 - -99) + 1) + -99;
                trouve = (double) random1 * random2;
                break;
            case 4:
                symbol = "/";
                random1 = new Random().nextInt((99 - -99) + 1) + -99;
                random2 = new Random().nextInt((99 - -99) + 1) + -99;
                if (random2 == 0) {
                    random2 = new Random().nextInt((99 - 1) + 1) + 1;
                }
                trouve = (double) random1 / random2;
                break;
        }
        TrouveFinale = Math.round(trouve * 100.0) / 100.0;
        System.out.println(TrouveFinale);
        textViewTrouve.setText(random1 + " " + symbol + " " + random2);

    }

    private boolean videTextView() {
        textViewCalcul.setText("");
        Nombre = 0;
        Virguleboolean = false;
        Dixieme = 0;
        Centieme = 0;
        NbDeVirgule = 0;
        Negative = false;
        return true;
    }

    private boolean TextViewScore() {

        textViewScore.setText("Score : " + Score);
        return true;
    }

    private boolean TextViewVies() {
        if (defaultLocale.equals(frLocale)) {
            textViewVies.setText("Vies : " + Vies);
        } else {
            textViewVies.setText("Life : " + Vies);
        }

        return true;
    }

    private boolean calculResultat() {
        Double resultat = 0.0;

        if (Virguleboolean != false) {
            if (NbDeVirgule == 1) {
                if (Dixieme / 100.0 < 0.10) {
                    resultat = (Nombre + (Dixieme / 10.0));
                } else {
                    resultat = (Nombre + (Dixieme / 100.0));
                }
            } else if (NbDeVirgule == 2 && Dixieme == 0) {
                resultat = (Nombre + (Centieme / 100.0));
            }
        } else {
            resultat = (double) (Nombre);
        }

        if (Negative == true) {
            resultat = -resultat;
        }
        if (resultat.equals(TrouveFinale)) {
            Score++;
            TextViewScore();
            TextViewVies();
            System.out.println(Score);
            TextViewTrouve();
            videTextView();


        } else {
            life();
            TextViewTrouve();
            videTextView();
            TextViewVies();
        }
        System.out.println(resultat);


        return true;
    }

    private void life() {
        Vies--;
        if (Vies <= 0) {

            finir();
        }
    }

    public void sauvegarde() {
        Calcul calcul = new Calcul();
        calcul.setScore(Score);
        calculService.storeScoreInDatabase(calcul);
    }

    public boolean finir() {
        sauvegarde();
        finish();
        return true;
    }
}