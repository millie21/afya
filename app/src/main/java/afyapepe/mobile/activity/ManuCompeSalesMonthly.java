package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import afyapepe.mobile.R;
import afyapepe.mobile.adapter.ManuCompeSalesAdapterM;
import afyapepe.mobile.adapter.SimpleCompeSalesAdapter;
import afyapepe.mobile.adapter.SimpleCompeSalesAdapter2;
import afyapepe.mobile.adapter.SimpleCompeSalesAdapter3;
import afyapepe.mobile.adapter.SimpleCompeSalesAdapter4;
import afyapepe.mobile.adapter.SimpleCompeSalesAdapter5;
import afyapepe.mobile.app.AppController;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

import static afyapepe.mobile.app.AppController.TAG;

/**
 * Created by Millie Agallo on 10/27/2017.
 */

public class ManuCompeSalesMonthly extends AppCompatActivity {

    private List<Stock> sectorList = new ArrayList<Stock>();
    private List<Stock> sectorList2 = new ArrayList<>();
    private List<Stock> sectorList3 = new ArrayList<>();
    private List<Stock> sectorList4 = new ArrayList<>();
    private List<Stock> sectorList5 = new ArrayList<>();
    private ListView listView;
    private ListView listView2;
    private ListView listView3;
    private ListView listView4;
    private ListView listView5;
    private SimpleCompeSalesAdapter adapter;
    private SimpleCompeSalesAdapter2 adapter2;
    private SimpleCompeSalesAdapter3 adapter3;
    private SimpleCompeSalesAdapter4 adapter4;
    private SimpleCompeSalesAdapter5 adapter5;
    private SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;
    FloatingActionButton fab;

    // MaterialSearchView searchView;
    SearchView searchView;

    MenuItem searchMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_comptoday_r);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new SQLiteHandler(ManuCompeSalesMonthly.this);

        session = new SessionManager(ManuCompeSalesMonthly.this);

        HashMap<String, String> user = db.getUserDetails();

        String email = user.get("email");

        View empty = findViewById(R.id.list_empty);
        listView = (ListView) findViewById(R.id.listview1);
        listView2 = (ListView) findViewById(R.id.listview2);
        listView3 = (ListView) findViewById(R.id.listview3);
        listView4 = (ListView) findViewById(R.id.listview4);
        listView5 = (ListView) findViewById(R.id.listview5);

        //not yet
        listView.setEmptyView(empty);
        listView2.setEmptyView(empty);
        listView3.setEmptyView(empty);
        listView4.setEmptyView(empty);
        listView5.setEmptyView(empty);


        fab = (FloatingActionButton) findViewById(R.id.fab);

        adapter = new SimpleCompeSalesAdapter(ManuCompeSalesMonthly.this, sectorList);
        adapter2 = new SimpleCompeSalesAdapter2(this,sectorList2);
        adapter3 = new SimpleCompeSalesAdapter3(this,sectorList3);
        adapter4 = new SimpleCompeSalesAdapter4(this,sectorList4);
        adapter5 = new SimpleCompeSalesAdapter5(this,sectorList5);

        listView.setAdapter(adapter);
        listView.setAdapter(adapter2);
        listView.setAdapter(adapter3);
        listView.setAdapter(adapter4);
        listView.setAdapter(adapter5);

        // Showing progress dialog
        //progressBar = new ProgressBar(ManuStocksView.this);

        pDialog = new ProgressDialog(ManuCompeSalesMonthly.this);

        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();

        //for error code 500
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, App_Config.company1m_url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        //progressBar.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            /*for (int i = 0; i < request.length(); i++) {*/

                            Stock stock = new Stock();

                            stock.setManufacturer(jsonObject.getString("Manufacturer"));
                            stock.setQuantity(jsonObject.getString("quantity"));
                            stock.setQprice(jsonObject.getString("qprice"));
                            sectorList.add(stock);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuCompeSalesMonthly.this, e.toString(), Toast.LENGTH_LONG).show();
                        }

                        adapter.notifyDataSetChanged();

                        pDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }
                Toast.makeText(ManuCompeSalesMonthly.this, error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuCompeSalesMonthly.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };
//        int socketTimeout = 30000;//30 seconds - change to what you want
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        response.setRetryPolicy(policy);
//        stringRequest.add(request);
        int socketTimeout = 90000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(stringRequest);

        //company2
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, App_Config.company2m_url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response2) {
                        Log.d(TAG, response2.toString());
                        //progressBar.dismiss();
                        try {
                            JSONObject jsonObject2 = new JSONObject(response2);
                            /*for (int i = 0; i < request.length(); i++) {*/

                            Stock stock = new Stock();

                            stock.setManufacturer(jsonObject2.getString("Manufacturer"));
                            stock.setQuantity(jsonObject2.getString("quantity"));
                            stock.setQprice(jsonObject2.getString("qprice"));
                            sectorList2.add(stock);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuCompeSalesMonthly.this, e.toString(), Toast.LENGTH_LONG).show();
                        }

                        adapter2.notifyDataSetChanged();

                        pDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }
                Toast.makeText(ManuCompeSalesMonthly.this, error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuCompeSalesMonthly.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };
//        int socketTimeout = 30000;//30 seconds - change to what you want
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        response.setRetryPolicy(policy);
//        stringRequest.add(request);
        int socketTimeout2 = 90000; // 30 seconds. You can change it
        RetryPolicy policy2 = new DefaultRetryPolicy(socketTimeout2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest2.setRetryPolicy(policy2);
        AppController.getInstance().addToRequestQueue(stringRequest2);

        //company3
        StringRequest stringRequest3 = new StringRequest(Request.Method.POST, App_Config.company3m_url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response3) {
                        Log.d(TAG, response3.toString());
                        //progressBar.dismiss();
                        try {
                            JSONObject jsonObject3 = new JSONObject(response3);
                            /*for (int i = 0; i < request.length(); i++) {*/

                            Stock stock = new Stock();

                            stock.setManufacturer(jsonObject3.getString("Manufacturer"));
                            stock.setQuantity(jsonObject3.getString("quantity"));
                            stock.setQprice(jsonObject3.getString("qprice"));
                            sectorList3.add(stock);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuCompeSalesMonthly.this, e.toString(), Toast.LENGTH_LONG).show();
                        }

                        adapter3.notifyDataSetChanged();

                        pDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }
                Toast.makeText(ManuCompeSalesMonthly.this, error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuCompeSalesMonthly.this);

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

        //company4
        StringRequest stringRequest4 = new StringRequest(Request.Method.POST, App_Config.company4m_url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response4) {
                        Log.d(TAG, response4.toString());
                        //progressBar.dismiss();
                        try {
                            JSONObject jsonObject4 = new JSONObject(response4);
                            /*for (int i = 0; i < request.length(); i++) {*/

                            Stock stock = new Stock();

                            stock.setManufacturer(jsonObject4.getString("Manufacturer"));
                            stock.setQuantity(jsonObject4.getString("quantity"));
                            stock.setQprice(jsonObject4.getString("qprice"));
                            sectorList4.add(stock);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuCompeSalesMonthly.this, e.toString(), Toast.LENGTH_LONG).show();
                        }

                        adapter4.notifyDataSetChanged();

                        pDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }
                Toast.makeText(ManuCompeSalesMonthly.this, error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuCompeSalesMonthly.this);

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

        //company5
        StringRequest stringRequest5 = new StringRequest(Request.Method.POST, App_Config.company6m_url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response5) {
                        Log.d(TAG, response5.toString());
                        //progressBar.dismiss();
                        try {
                            JSONObject jsonObject5 = new JSONObject(response5);
                            /*for (int i = 0; i < request.length(); i++) {*/

                            Stock stock = new Stock();

                            stock.setManufacturer(jsonObject5.getString("Manufacturer"));
                            stock.setQuantity(jsonObject5.getString("quantity"));
                            stock.setQprice(jsonObject5.getString("qprice"));
                            sectorList5.add(stock);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuCompeSalesMonthly.this, e.toString(), Toast.LENGTH_LONG).show();
                        }

                        adapter5.notifyDataSetChanged();

                        pDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }
                Toast.makeText(ManuCompeSalesMonthly.this, error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuCompeSalesMonthly.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };
//
        int socketTimeout5 = 90000; // 30 seconds. You can change it
        RetryPolicy policy5 = new DefaultRetryPolicy(socketTimeout5,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest5.setRetryPolicy(policy5);
        AppController.getInstance().addToRequestQueue(stringRequest5);

    }
//
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
//            filteredstocklist = sectorList;
//        }
//        else {
//            ArrayList<Stock> filteredList = new ArrayList<>();
//
//            for (Stock stock : sectorList){
//
//                if(stock.getManufacturer().toLowerCase().contains(charString.toLowerCase())){
//                    filteredList.add(stock);
//                }
//            }
//            filteredstocklist = filteredList;
//        }
//
//        sectorList.clear();
//
//        sectorList.addAll(filteredstocklist);
//
//        adapter.notifyDataSetChanged();
//
//        return filteredstocklist;
//    }
    public void fab(View view){
        Intent intent5 = new Intent(getApplicationContext(), Manufacturers.class);
        startActivity(intent5);
    }
}
//        extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_manu_compe_sales);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        // Find the view pager that will allow the user to swipe between fragments
//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//
//        // Create an adapter that knows which fragment should be shown on each page
//        // SimpleFragmentAdapter adapter = new SimpleFragmentAdapter(this, getSupportFragmentManager());
//        ManuCompeSalesAdapterM vadapter = new ManuCompeSalesAdapterM(this, getSupportFragmentManager());
//
//        // Set the adapter onto the view pager
//        // viewPager.setAdapter(adapter);
//        viewPager.setAdapter(vadapter);
//
//        // Give the TabLayout the ViewPager
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
//        tabLayout.setupWithViewPager(viewPager);
//
//    }
//}
//
