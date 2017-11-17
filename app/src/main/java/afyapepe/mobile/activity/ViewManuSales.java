package afyapepe.mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import afyapepe.mobile.R;
import afyapepe.mobile.fragment.ManuBRegion;
import afyapepe.mobile.fragment.ManuByDoctor;
import afyapepe.mobile.fragment.ManuByDrug;
import afyapepe.mobile.fragment.ManuByPharmacy;

public class ViewManuSales extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_manu_sales);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
//    public void cardview(View v)
//    {
//        Intent intent1 = new Intent(this, ManuCompeSalesToday.class);
//        startActivity(intent1);
//    }

    public void cardview2(View v) {
        Intent intent2 = new Intent(getApplicationContext(), ManuCountyInfo.class);
        startActivity(intent2);
//Toast.makeText(ViewManuSales.this,"Almost done...", Toast.LENGTH_LONG).show();
    }

    public void cardview3(View v) {
        Intent intent3 = new Intent(getApplicationContext(), afyapepe.mobile.fragment.ManuByRegion.class);
        startActivity(intent3);
    }

    public void cardview4(View v) {
        Intent intent4 = new Intent(getApplicationContext(), ManuByDrug.class);
        startActivity(intent4);
    }

    public void cardview5(View v) {
        Intent intent5 = new Intent(getApplicationContext(), ManuByDoctor.class);
        startActivity(intent5);
    }

    public void cardview6(View v) {
        Intent intent4 = new Intent(getApplicationContext(), ManuByPharmacy.class);
        startActivity(intent4);
        // Toast.makeText(ViewManuSales.this,"Almost done...", Toast.LENGTH_LONG).show();
    }
}
