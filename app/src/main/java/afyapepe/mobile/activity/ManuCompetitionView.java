package afyapepe.mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import afyapepe.mobile.R;

public class ManuCompetitionView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_competition_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    public void cardview1(View v)
    {
        Intent intent = new Intent(getApplicationContext(), ManuCompeSales.class);
        startActivity(intent);
    }

    public void cardview2(View v)
    {
        Intent intent = new Intent(getApplicationContext(), ManuCompeRegion.class);
        startActivity(intent);
    }

    public void cardview3(View v)
    {
        Intent intent = new Intent(getApplicationContext(), ManuCompeDoctor.class);
        startActivity(intent);
    }

    public void cardview4(View v)
    {
        Intent intent = new Intent(getApplicationContext(), ManuCompeDrugs.class);
        startActivity(intent);
    }

}
