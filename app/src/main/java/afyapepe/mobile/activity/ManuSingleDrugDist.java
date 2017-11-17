package afyapepe.mobile.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.TextView;

import afyapepe.mobile.R;

/**
 * Created by Millie Agallo on 9/28/2017.
 */

public class ManuSingleDrugDist extends AppCompatActivity {


    TextView tvchoicename,tvlivebreed,tvlocation,tvphoneno,tvprice,tvchoicedesc, tvprice2, tvprice3, tvprice4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_single_drugd);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.4));

        tvchoicename = (TextView) findViewById(R.id.tvposition);
        tvlivebreed = (TextView) findViewById(R.id.tvid);
        tvlocation = (TextView) findViewById(R.id.tvname2);
        tvphoneno = (TextView) findViewById(R.id.tvpos);
        tvprice = (TextView) findViewById(R.id.tvname);
        tvprice2 = (TextView) findViewById(R.id.tvname3);
        tvprice3 = (TextView) findViewById(R.id.tvpos2);
        tvprice4 = (TextView) findViewById(R.id.tvpos3);
        tvchoicedesc = (TextView) findViewById(R.id.tvpos4);


        //  "id","drugname","name","FacilityName","quantity","price","total","reason","substitution_reason"},
        Bundle bundle = getIntent().getExtras();
        String livename = bundle.getString("id");
        String breedname = bundle.getString("drugname");
        String firstname = bundle.getString("name");
        String location = bundle.getString("FacilityName");
        String phoneno = bundle.getString("quantity");
        String pricing = bundle.getString("price");
        String pricing2 = bundle.getString("total");
        String pricing3 = bundle.getString("reason");
        String pricing4 = bundle.getString("substitution_reason");
//        String datesubmitted = bundle.getString("datesubmitted");
//        String description = bundle.getString("description");

//        tvchoicename.setText("id :  " +       livename);
//        tvlivebreed.setText( "Facility :  " + breedname);
//        tvchoicedesc.setText("Drugname :  " +      firstname);
//        tvlocation.setText(   "Doctor :  " +        location);
//        tvphoneno.setText(   "Pharmacy :  " +     phoneno);
//        tvprice.setText(      "County :  " +         pricing);
//        tvprice2.setText(      "County :  " +         pricing2);
//        tvprice3.setText(      "County :  " +         pricing3);
//        tvprice4.setText(      "County :  " +         pricing4);

        tvchoicename.setText(  livename);
        tvlivebreed.setText(   breedname);
        tvchoicedesc.setText(  firstname);
        tvlocation.setText(    location);
        tvphoneno.setText(     phoneno);
        tvprice.setText(  pricing);
        tvprice2.setText(  pricing2);
        tvprice3.setText(  pricing3);
        tvprice4.setText(  pricing4);

    }
}
