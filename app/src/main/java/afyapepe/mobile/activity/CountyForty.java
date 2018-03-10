package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import afyapepe.mobile.R;
import afyapepe.mobile.adapter.SalesAdapterRTotal;
import afyapepe.mobile.adapter.SimpleSalesAdapterR;
import afyapepe.mobile.app.AppController;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

import static afyapepe.mobile.app.AppController.TAG;

/**
 * Created by Millie Agallo on 2/18/2018.
 */

public class CountyForty extends AppCompatActivity {

    private List<Stock> salesListr = new ArrayList<Stock>();
    private List<Stock> totalsaleslist = new ArrayList<Stock>();
    private ListView listView;
    private ListView listViewcash;
    private SimpleSalesAdapterR adapter;
    private SalesAdapterRTotal adapterRTotal;
    private SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manu_countyone);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new SQLiteHandler(CountyForty.this);

        session = new SessionManager(CountyForty.this);

        //Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();

        String email = user.get("email");

        View empty = findViewById(R.id.list_empty);
        listView = (ListView) findViewById(R.id.listview11);

        // listViewcash = (ListView) findViewById(R.id.listviewyatottals);
        // TaskListView.setVisibility((adapter.isEmpty())?View.GONE:View.VISIBLE);
        listView.setEmptyView(empty);
        adapter = new SimpleSalesAdapterR(CountyForty.this, salesListr);
        listView.setAdapter(adapter);

        //      adapterRTotal = new SalesAdapterRTotal(ManuByRegion.this, totalsaleslist);
//        listViewcash.setAdapter(adapterRTotal);

        pDialog = new ProgressDialog(CountyForty.this);

        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, App_Config.county_40,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        pDialog.dismiss();
                        try {

                            JSONArray request = new JSONArray(response);
                            for (int i = 0; i < request.length(); i++) {

                                Stock stock = new Stock();
                                JSONObject jsonObject = null;
                                jsonObject = request.getJSONObject(i);
                                stock.setDrugname(jsonObject.getString("drugname"));
                                stock.setName(jsonObject.getString("name"));
                                stock.setFacilityName(jsonObject.getString("FacilityName"));
                                stock.setCounty(jsonObject.getString("county"));
                                stock.setPharmacy(jsonObject.getString("pharmacy"));
                                stock.setTotal(jsonObject.getString("total"));

                                salesListr.add(stock);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CountyForty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing12);
                        getTotalCount.setText("" + listView.getCount());

//                        TextView getTotalCash = (TextView) findViewById(R.id.totalcash);
//
//                       getTotalCash.setText(""+testtotal);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(CountyForty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(CountyForty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout = 90000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(stringRequest);


//        StringRequest srequest = new StringRequest(Request.Method.POST, App_Config.testtotal,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d(TAG, response.toString());
//
//                        try {
//                            JSONArray request = new JSONArray(response);
//                            for (int i = 0; i < request.length(); i++) {
//
//                                Stock stock = new Stock();
//                                JSONObject jsonObject = null;
//                                jsonObject = request.getJSONObject(i);
//                                stock.setTotalIncome(jsonObject.getString("TotalIncome"));
//
//                                // String totalIncome = test.getString("TotalIncome");
//                                totalsaleslist.add(stock);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(ManuByRegion.this, e.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                        adapterRTotal.notifyDataSetChanged();
//                    }
//
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        if (pDialog != null) {
//                            pDialog.dismiss();
//                            pDialog = null;
//                        }
//                        Toast.makeText(ManuByRegion.this, error.toString(), Toast.LENGTH_LONG).show();
//                        error.printStackTrace();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                db = new SQLiteHandler(ManuByRegion.this);
//
//                // Fetching user details from SQLite
//                HashMap<String, String> user = db.getUserDetails();
//
//                String email = user.get("email");
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("email", email);
//
//                return params;
//            }
        //   };
//
//        AppController.getInstance().addToRequestQueue(srequest);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) MenuItemCompat.getActionView(search);
        search(searchView);


        return true;
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filteredDrugs(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

//                if(newText.isEmpty()){
//
//                }
                return false;
            }
        });

    }


    public List<Stock> filteredDrugs(CharSequence charSequence){
        List<Stock> filteredstocklist;
        String charString = charSequence.toString();

        if (charString.isEmpty()){
            filteredstocklist = salesListr;
        }
        else {
            ArrayList<Stock> filteredList = new ArrayList<>();

            for (Stock stock : salesListr){

                if(stock.getCounty().toLowerCase().contains(charString.toLowerCase())){
                    filteredList.add(stock);
                }
            }
            filteredstocklist = filteredList;
        }

        salesListr.clear();

        salesListr.addAll(filteredstocklist);

        adapter.notifyDataSetChanged();

        return filteredstocklist;
    }
    public void fab(View view){
        Intent intent5 = new Intent(getApplicationContext(), Manufacturers.class);
        startActivity(intent5);
    }
}
