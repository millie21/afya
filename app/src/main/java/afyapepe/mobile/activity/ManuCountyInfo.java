package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import afyapepe.mobile.adapter.RegionSumCounties;
import afyapepe.mobile.adapter.RegionSumDoctors;
import afyapepe.mobile.adapter.RegionSumDrugs;
import afyapepe.mobile.adapter.RegionSumPharmacies;
import afyapepe.mobile.adapter.RegionSumPrescriptions;
import afyapepe.mobile.adapter.SimpleSalesAdapterCountyInfo;
import afyapepe.mobile.adapter.SimpleSalesAdapterR;
import afyapepe.mobile.adapter.TotalRegionSales;
import afyapepe.mobile.app.AppController;
import afyapepe.mobile.fragment.ManuByRegion;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

import static afyapepe.mobile.app.AppController.TAG;

public class ManuCountyInfo extends AppCompatActivity {


    private List<Stock> salesListr = new ArrayList<Stock>();
    private List<Stock> countyListsum = new ArrayList<Stock>();
    private List<Stock> doctorListsum = new ArrayList<>();
    private List<Stock> pharmacyListsum = new ArrayList<>();
    private List<Stock> drugsListsum = new ArrayList<>();
    private List<Stock> prescriptionsListsum = new ArrayList<>();
    private List<Stock> salesTotals = new ArrayList<>();

    private ListView listView;
    private ListView listView2;
    private ListView listView3;
    private ListView listView4;
//    private ListView listView5;
    private ListView listView6;
    private ListView listView8;

    private SimpleSalesAdapterCountyInfo adapter;
    private RegionSumCounties adapterc;
    private RegionSumDoctors adapterd;
    private RegionSumPharmacies adapterph;
    private RegionSumDrugs adapterdg;
    private RegionSumPrescriptions adapterp;
    private TotalRegionSales regdapter;

    private SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manu_county_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new SQLiteHandler(ManuCountyInfo.this);

        session = new SessionManager(ManuCountyInfo.this);

        //Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();

        String email = user.get("email");

        listView = (ListView) findViewById(R.id.listview1);
        listView2 = (ListView)findViewById(R.id.listview2);
        listView3 = (ListView)findViewById(R.id.listview3);
        listView4 = (ListView)findViewById(R.id.listview4);
       // listView5 = (ListView)findViewById(R.id.listview5);
        listView6 = (ListView)findViewById(R.id.listview6);
        listView8 = (ListView)findViewById(R.id.listview8);

        adapterc = new RegionSumCounties(ManuCountyInfo.this, countyListsum);
        adapterd = new RegionSumDoctors(ManuCountyInfo.this,doctorListsum);
        adapterph = new RegionSumPharmacies(ManuCountyInfo.this,pharmacyListsum);
        adapterdg = new RegionSumDrugs(this,drugsListsum);
        adapterp = new RegionSumPrescriptions(this,prescriptionsListsum);
        regdapter = new TotalRegionSales(this,salesTotals);


        listView.setAdapter(adapterc);
        listView2.setAdapter(adapterph);
        listView3.setAdapter(adapterd);
        listView4.setAdapter(adapterp);
      //  listView5.setAdapter();
        listView6.setAdapter(adapterdg);
        listView8.setAdapter(regdapter);

        pDialog = new ProgressDialog(ManuCountyInfo.this);

        pDialog.setMessage("Loading...");
        pDialog.show();

        //county count
        StringRequest stringRequest = new StringRequest(Request.Method.POST, App_Config.county_count,
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

                                stock.setTotcounty(jsonObject.getString("totcounty"));

                                countyListsum.add(stock);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuCountyInfo.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapterc.notifyDataSetChanged();
                   //     Toast.makeText(ManuCountyInfo.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

//                        TextView getTotalCount = (TextView) findViewById(R.id.testing12);
//                        getTotalCount.setText(""+listView.getCount());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuCountyInfo.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuCountyInfo.this);

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

        //pharmacies count
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, App_Config.pharmacy_count,
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

                                stock.setTotpharmacies(jsonObject.getString("totpharmacies"));

                               pharmacyListsum.add(stock);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuCountyInfo.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapterph.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

//                        TextView getTotalCount = (TextView) findViewById(R.id.testing12);
//                        getTotalCount.setText(""+listView.getCount());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuCountyInfo.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuCountyInfo.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };

        int socketTimeout2 = 90000; // 30 seconds. You can change it
        RetryPolicy policy2 = new DefaultRetryPolicy(socketTimeout2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest2.setRetryPolicy(policy2);
        AppController.getInstance().addToRequestQueue(stringRequest2);

        //doctors count
        StringRequest stringRequest3 = new StringRequest(Request.Method.POST, App_Config.doctors_count,
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

                                stock.setTotdoctors(jsonObject.getString("totdoctors"));

                                doctorListsum.add(stock);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuCountyInfo.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapterd.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

//                        TextView getTotalCount = (TextView) findViewById(R.id.testing12);
//                        getTotalCount.setText(""+listView.getCount());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuCountyInfo.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuCountyInfo.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };

        int socketTimeout3 = 90000; // 30 seconds. You can change it
        RetryPolicy policy3 = new DefaultRetryPolicy(socketTimeout3,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest3.setRetryPolicy(policy3);
        AppController.getInstance().addToRequestQueue(stringRequest3);

        //prescriptions count
        StringRequest stringRequest4 = new StringRequest(Request.Method.POST, App_Config.prescriptions_count,
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

                                stock.setTotprescriptions(jsonObject.getString("totprescriptions"));

                                prescriptionsListsum.add(stock);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuCountyInfo.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapterp.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

//                        TextView getTotalCount = (TextView) findViewById(R.id.testing12);
//                        getTotalCount.setText(""+listView.getCount());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuCountyInfo.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuCountyInfo.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };

        int socketTimeout4 = 90000; // 30 seconds. You can change it
        RetryPolicy policy4 = new DefaultRetryPolicy(socketTimeout4,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest4.setRetryPolicy(policy4);
        AppController.getInstance().addToRequestQueue(stringRequest4);

        //drugs count
        StringRequest stringRequest5 = new StringRequest(Request.Method.POST, App_Config.drugs_count,
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

                                stock.setTotdrugs(jsonObject.getString("totdrugs"));

                                drugsListsum.add(stock);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuCountyInfo.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapterdg.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

//                        TextView getTotalCount = (TextView) findViewById(R.id.testing12);
//                        getTotalCount.setText(""+listView.getCount());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuCountyInfo.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuCountyInfo.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };

        int socketTimeout5 = 90000; // 30 seconds. You can change it
        RetryPolicy policy5 = new DefaultRetryPolicy(socketTimeout5,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest5.setRetryPolicy(policy5);
        AppController.getInstance().addToRequestQueue(stringRequest5);

        //sales totals
        StringRequest stringRequest8 = new StringRequest(Request.Method.POST, App_Config.region_sales_url,
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

                                stock.setTotalIncome(jsonObject.getString("TotalIncome"));

                                salesTotals.add(stock);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuCountyInfo.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        regdapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

//                        TextView getTotalCount = (TextView) findViewById(R.id.testing12);
//                        getTotalCount.setText(""+listView.getCount());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuCountyInfo.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuCountyInfo.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };

        int socketTimeout8 = 90000; // 30 seconds. You can change it
        RetryPolicy policy8 = new DefaultRetryPolicy(socketTimeout8,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest8.setRetryPolicy(policy8);
        AppController.getInstance().addToRequestQueue(stringRequest8);

    }
    public void fab(View view){
        Intent intent5 = new Intent(getApplicationContext(), Manufacturers.class);
        startActivity(intent5);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search,menu);
//        MenuItem search = menu.findItem(R.id.action_search);
//        SearchView searchView =(SearchView) MenuItemCompat.getActionView(search);
//        search(searchView);
//
//
//        return true;
//    }
//
//    private void search(SearchView searchView){
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                filteredDrugs(query);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
////                if(newText.isEmpty()){
////
////                }
//                return false;
//            }
//        });
//    }
//    public List<Stock> filteredDrugs(CharSequence charSequence){
//        List<Stock> filteredstocklist;
//        String charString = charSequence.toString();
//
//        if (charString.isEmpty()){
//            filteredstocklist = salesListr;
//        }
//        else {
//            ArrayList<Stock> filteredList = new ArrayList<>();
//
//            for (Stock stock : salesListr){
//
//                if(stock.getCounty().toLowerCase().contains(charString.toLowerCase())){
//                    filteredList.add(stock);
//                }
//            }
//            filteredstocklist = filteredList;
//        }
//        salesListr.clear();
//
//        salesListr.addAll(filteredstocklist);
//
//        adapter.notifyDataSetChanged();
//
//        return filteredstocklist;
//    }
}
