package afyapepe.mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
    public void cardview(View v)
    {
        Intent intent1 = new Intent(ManuCompetitionView.this, ManuCompeAnSales.class);
        startActivity(intent1);
    }

    public void cardview2(View v)
    {
        Intent intent2 = new Intent(getApplicationContext(), ManuCompeRegion.class);
        startActivity(intent2);
    }

    public void cardview3(View v)
    {
        Intent intent3 = new Intent(getApplicationContext(), ManuCompeDoctor.class);
        startActivity(intent3);
    }

    public void cardview4(View v)
    {
//        Intent intent4 = new Intent(getApplicationContext(), ManuCompeDrugs.class);
//        startActivity(intent4);
        Toast.makeText(this,"Almost Done",Toast.LENGTH_SHORT).show();
    }


}
