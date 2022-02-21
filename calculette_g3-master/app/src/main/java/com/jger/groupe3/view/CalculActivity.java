package com.jger.groupe3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jger.groupe3.DivideException;
import com.jger.groupe3.R;
import com.jger.groupe3.TypeOperationEnum;
import com.jger.groupe3.database.CalculBaseHelper;
import com.jger.groupe3.database.CalculDao;
import com.jger.groupe3.entity.Calcul;
import com.jger.groupe3.service.CalculService;

public class CalculActivity extends AppCompatActivity {
    private Integer premierElement = 0;
    private Integer deuxiemeElement = 0;
    private TypeOperationEnum typeOperation;
    private TextView textViewCalcul;
    private Integer BORNE_HAUTE = 9999;
    private CalculService calculService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        calculService = new CalculService(new CalculDao(new CalculBaseHelper(this)));
        textViewCalcul = findViewById(R.id.textViewCalcul);
        Button bouton1 = findViewById(R.id.bouton_1);
        bouton1.setOnClickListener(view -> ajouteValeur(1));
        Button bouton2 = findViewById(R.id.bouton_2);
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
        Button boutonAdd = findViewById(R.id.addButton);
        boutonAdd.setOnClickListener(view -> ajouterTypeOperation(TypeOperationEnum.ADD));
        Button boutonSubstract = findViewById(R.id.susbstractButton);
        boutonSubstract.setOnClickListener(view -> ajouterTypeOperation(TypeOperationEnum.SUBSTRACT));
        Button boutonMultiply = findViewById(R.id.multiplyButton);
        boutonMultiply.setOnClickListener(view -> ajouterTypeOperation(TypeOperationEnum.MULTIPLY));
        Button boutonDivide = findViewById(R.id.diviseButton);
        boutonDivide.setOnClickListener(view -> ajouterTypeOperation(TypeOperationEnum.DIVIDE));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar,menu);
        MenuItem toolbarCalculer = menu.findItem(R.id.toolbarCalculer);
        MenuItem toolbarVider = menu.findItem(R.id.toolbarVider);
        toolbarCalculer.setOnMenuItemClickListener(menuItem -> calculResultat());
        toolbarVider.setOnMenuItemClickListener(menuItem -> videTextView());
        return true;
    }

    private void ajouterTypeOperation(TypeOperationEnum typeOperation){
        this.typeOperation=typeOperation;
        majTextView();
    }

    private void ajouteValeur(Integer valeur){
        if(typeOperation == null){
            if(10*premierElement+valeur > BORNE_HAUTE){
                Toast.makeText(this,getString(R.string.ERREUR_TROP_GRAND),Toast.LENGTH_LONG).show();
            }else{
                premierElement = 10*premierElement+valeur;
            }
        }else{
            if(10*deuxiemeElement+valeur > BORNE_HAUTE){
                Toast.makeText(this,getString(R.string.ERREUR_TROP_GRAND),Toast.LENGTH_LONG).show();
            }else{
                deuxiemeElement = 10*deuxiemeElement+valeur;
            }
        }

        majTextView();
    }

    private void majTextView() {
        String textAAfficher="";
        if(typeOperation == null){
            textAAfficher = premierElement.toString();
        }else{
            textAAfficher = premierElement + " "+typeOperation.getSymbol()+" "+deuxiemeElement;
        }
        textViewCalcul.setText(textAAfficher);
    }

    private boolean videTextView() {
        textViewCalcul.setText("");
        premierElement=0;
        typeOperation = null;
        deuxiemeElement = 0;
        return true;
    }

    private boolean calculResultat() {
        try{
            Double resultat = 0.0;
            if(typeOperation!=null){
                switch (typeOperation){
                    case ADD:
                        resultat = (double) (premierElement + deuxiemeElement);
                        break;
                    case MULTIPLY:
                        resultat = (double) (premierElement*deuxiemeElement);
                        break;
                    case SUBSTRACT:
                        resultat = (double) (premierElement-deuxiemeElement);
                        break;
                    case DIVIDE:
                        if(deuxiemeElement==0){
                            throw  new DivideException();
                        }else{
                            resultat = (double) premierElement/deuxiemeElement;
                        }
                        break;

                }
                Calcul calcul = new Calcul();
                calcul.setResultat(resultat);
                calcul.setSymbol(typeOperation.getSymbol());
                calcul.setPremierElement(premierElement);
                calcul.setDeuxiemeElement(deuxiemeElement);
                calculService.storeCalculInDatabase(calcul);
                ouvreLastComputeActivity(resultat);
            }else{
                Toast.makeText(this,getString(R.string.ERREUR_INCOMPLET),Toast.LENGTH_LONG).show();
            }

        }catch(DivideException e){
            Toast.makeText(this,getString(R.string.ERREUR_DIVISION_ZERO),Toast.LENGTH_LONG).show();
        }

        return true;
    }

    private void ouvreLastComputeActivity(double resultat) {
        Intent i = new Intent(this, LastComputeActivity.class);
        i.putExtra("premierElement",premierElement);
        i.putExtra("deuxiemeElement",deuxiemeElement);
        i.putExtra("symbol",typeOperation.getSymbol());
        i.putExtra("resultat",resultat);
        startActivity(i);
    }
}