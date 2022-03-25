package com.jger.groupe3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.jger.groupe3.R;
import com.jger.groupe3.database.CalculBaseHelper;
import com.jger.groupe3.database.CalculDao;
import com.jger.groupe3.service.CalculService;

import java.util.Locale;

public class StatistiqueActivity extends AppCompatActivity {
    private CalculService caclculService;
    Locale frLocale = new Locale("fr", "FR");
    Locale defaultLocale = Locale.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistique);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        caclculService = new CalculService(new CalculDao(new CalculBaseHelper(this)));


        TextView nombreCalculTextview = findViewById(R.id.textView_statistique);

        int top;
        if (caclculService.getCalculNumber() > 10) {
            top = 10;
        } else {
            top = (int) caclculService.getCalculNumber();
        }

        if (frLocale.equals(defaultLocale)) {
            nombreCalculTextview.setText("Top " + top + " des meilleure Score" + caclculService.gettop());
        } else {
            nombreCalculTextview.setText("Top " + top + " of the best Score" + caclculService.gettop());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar2, menu);
        MenuItem toolbarRetour = menu.findItem(R.id.toolbarRetour);
        toolbarRetour.setOnMenuItemClickListener(menuItem -> finir());

        return true;
    }

    public boolean finir() {
        finish();
        return true;
    }


}