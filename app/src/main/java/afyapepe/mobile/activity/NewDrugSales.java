package afyapepe.mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import afyapepe.mobile.R;
import afyapepe.mobile.fragment.ManuByPharmacy;
import afyapepe.mobile.fragment.ManuByPharmacy50;

public class NewDrugSales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_drug_sales);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void pharm1(View view)
    {
        Intent joboneIntent = new Intent(NewDrugSales.this, Drug1.class);
        startActivity(joboneIntent);

    }

    public void pharm50(View view)
    {
        Intent joboneIntent = new Intent(NewDrugSales.this, Drug2.class);
        startActivity(joboneIntent);

    }
    public void pharm3(View view)
    {
        Intent joboneIntent = new Intent(NewDrugSales.this, Drug3.class);
        startActivity(joboneIntent);

    }
    public void pharm4(View view)
    {
        Intent joboneIntent = new Intent(NewDrugSales.this, Drug4.class);
        startActivity(joboneIntent);

    }

}
