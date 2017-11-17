package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import afyapepe.mobile.R;

public class ManuSingleStockView extends AppCompatActivity {

    TextView tvchoicename,tvlivebreed,tvlocation,tvphoneno,tvprice,tvchoicedesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_manu_single_stock_view);

      // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
//getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.4));


        tvchoicename = (TextView) findViewById(R.id.tvposition);
        tvlivebreed = (TextView) findViewById(R.id.tvid);
        tvlocation = (TextView) findViewById(R.id.tvname);
        tvphoneno = (TextView) findViewById(R.id.tvname2);
        tvprice = (TextView) findViewById(R.id.tvname3);
        tvchoicedesc = (TextView) findViewById(R.id.tvpos);

//        String county = ((TextView) view.findViewById(R.id.tvname)).getText().toString();
//        String phonenumber = ((TextView) view.findViewById(R.id.tvname2)).getText().toString();
//        String email = ((TextView) view.findViewById(R.id.tvname3)).getText().toString();
//TaskListView new String[]{"id","drugname","pharm", "name", "county", "quantity", "strength"},
        Bundle bundle = getIntent().getExtras();
        String livename = bundle.getString("id");
        String firstname = bundle.getString("pharm");
        String breedname = bundle.getString("drugname");
        String location = bundle.getString("name");
        String phoneno = bundle.getString("county");
        String pricing = bundle.getString("quantity");
//        String datesubmitted = bundle.getString("datesubmitted");
//        String description = bundle.getString("description");

//        tvchoicename.setText("id :  " +       livename);
//        tvchoicedesc.setText("Drugname :  " +      breedname);
//        tvlivebreed.setText( "Facility :  " + firstname);
//        tvlocation.setText(   "Doctor :  " +        location);
//        tvphoneno.setText(   "Pharmacy :  " +     phoneno);
//        tvprice.setText(      "County :  " +         pricing);

        tvchoicename.setText(livename);
        tvchoicedesc.setText( breedname);
        tvlivebreed.setText(  firstname);
        tvlocation.setText(  location);
        tvphoneno.setText(  phoneno);
        tvprice.setText(  pricing);



    }
}
