package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
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
import afyapepe.mobile.adapter.SalesAdapterRTotal;
import afyapepe.mobile.adapter.SimpleSalesAdapterR;
import afyapepe.mobile.app.AppController;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

import static afyapepe.mobile.app.AppController.TAG;

public class ManuByCounty extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu_by_county);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new SQLiteHandler(ManuByCounty.this);

        session = new SessionManager(ManuByCounty.this);

        //Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();

        String email = user.get("email");

        listView = (ListView) findViewById(R.id.listview11);
        adapter = new SimpleSalesAdapterR(ManuByCounty.this, salesListr);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(ManuByCounty.this);

        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, App_Config.county_one,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing12);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

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

//county 2
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, App_Config.county_2,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing2);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

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

        //county3
        StringRequest stringRequest3 = new StringRequest(Request.Method.POST, App_Config.county_3,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing3);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

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

        StringRequest stringRequest4 = new StringRequest(Request.Method.POST, App_Config.county_4,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing4);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

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


        StringRequest stringRequest5 = new StringRequest(Request.Method.POST, App_Config.county_5,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing5);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

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


        StringRequest stringRequest6 = new StringRequest(Request.Method.POST, App_Config.county_6,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing6);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout6 = 90000; // 30 seconds. You can change it
        RetryPolicy policy6 = new DefaultRetryPolicy(socketTimeout6,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest6.setRetryPolicy(policy6);
        AppController.getInstance().addToRequestQueue(stringRequest6);

        StringRequest stringRequest7 = new StringRequest(Request.Method.POST, App_Config.county_7,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing7);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout7 = 90000; // 30 seconds. You can change it
        RetryPolicy policy7 = new DefaultRetryPolicy(socketTimeout7,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest7.setRetryPolicy(policy7);
        AppController.getInstance().addToRequestQueue(stringRequest7);

        StringRequest stringRequest8 = new StringRequest(Request.Method.POST, App_Config.county_8,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing8);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

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

        StringRequest stringRequest9 = new StringRequest(Request.Method.POST, App_Config.county_9,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing9);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout9 = 90000; // 30 seconds. You can change it
        RetryPolicy policy9 = new DefaultRetryPolicy(socketTimeout9,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest9.setRetryPolicy(policy9);
        AppController.getInstance().addToRequestQueue(stringRequest9);


        StringRequest stringRequest10 = new StringRequest(Request.Method.POST, App_Config.county_10,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing10);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout10 = 90000; // 30 seconds. You can change it
        RetryPolicy policy10 = new DefaultRetryPolicy(socketTimeout10,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest10.setRetryPolicy(policy10);
        AppController.getInstance().addToRequestQueue(stringRequest10);


        StringRequest stringRequest11 = new StringRequest(Request.Method.POST, App_Config.county_11,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing11);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout11 = 90000; // 30 seconds. You can change it
        RetryPolicy policy11 = new DefaultRetryPolicy(socketTimeout11,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest11.setRetryPolicy(policy11);
        AppController.getInstance().addToRequestQueue(stringRequest11);


        StringRequest stringRequest12 = new StringRequest(Request.Method.POST, App_Config.county_12,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing12i);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout12 = 90000; // 30 seconds. You can change it
        RetryPolicy policy12 = new DefaultRetryPolicy(socketTimeout12,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest12.setRetryPolicy(policy12);
        AppController.getInstance().addToRequestQueue(stringRequest12);


        StringRequest stringRequest13 = new StringRequest(Request.Method.POST, App_Config.county_13,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing13);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout13 = 90000; // 30 seconds. You can change it
        RetryPolicy policy13 = new DefaultRetryPolicy(socketTimeout13,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest13.setRetryPolicy(policy13);
        AppController.getInstance().addToRequestQueue(stringRequest13);


        StringRequest stringRequest14 = new StringRequest(Request.Method.POST, App_Config.county_14,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing14);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout14 = 90000; // 30 seconds. You can change it
        RetryPolicy policy14 = new DefaultRetryPolicy(socketTimeout14,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest14.setRetryPolicy(policy14);
        AppController.getInstance().addToRequestQueue(stringRequest14);


        StringRequest stringRequest15 = new StringRequest(Request.Method.POST, App_Config.county_15,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing15);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout15 = 90000; // 30 seconds. You can change it
        RetryPolicy policy15 = new DefaultRetryPolicy(socketTimeout15,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest15.setRetryPolicy(policy15);
        AppController.getInstance().addToRequestQueue(stringRequest15);


        StringRequest stringRequest16 = new StringRequest(Request.Method.POST, App_Config.county_16,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing16);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout16 = 90000; // 30 seconds. You can change it
        RetryPolicy policy16 = new DefaultRetryPolicy(socketTimeout16,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest16.setRetryPolicy(policy16);
        AppController.getInstance().addToRequestQueue(stringRequest16);


        StringRequest stringRequest17 = new StringRequest(Request.Method.POST, App_Config.county_17,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing17);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout17 = 90000; // 30 seconds. You can change it
        RetryPolicy policy17 = new DefaultRetryPolicy(socketTimeout17,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest17.setRetryPolicy(policy17);
        AppController.getInstance().addToRequestQueue(stringRequest17);


        StringRequest stringRequest18 = new StringRequest(Request.Method.POST, App_Config.county_18,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing18);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout18 = 90000; // 30 seconds. You can change it
        RetryPolicy policy18 = new DefaultRetryPolicy(socketTimeout18,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest18.setRetryPolicy(policy18);
        AppController.getInstance().addToRequestQueue(stringRequest18);


        StringRequest stringRequest19 = new StringRequest(Request.Method.POST, App_Config.county_19,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing19);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout19 = 90000; // 30 seconds. You can change it
        RetryPolicy policy19 = new DefaultRetryPolicy(socketTimeout19,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest19.setRetryPolicy(policy19);
        AppController.getInstance().addToRequestQueue(stringRequest19);


        StringRequest stringRequest20 = new StringRequest(Request.Method.POST, App_Config.county_20,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing20);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout20 = 90000; // 30 seconds. You can change it
        RetryPolicy policy20 = new DefaultRetryPolicy(socketTimeout20,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest20.setRetryPolicy(policy20);
        AppController.getInstance().addToRequestQueue(stringRequest20);


        StringRequest stringRequest21 = new StringRequest(Request.Method.POST, App_Config.county_21,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing21);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout21 = 90000; // 30 seconds. You can change it
        RetryPolicy policy21 = new DefaultRetryPolicy(socketTimeout21,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest21.setRetryPolicy(policy21);
        AppController.getInstance().addToRequestQueue(stringRequest21);


        StringRequest stringRequest22 = new StringRequest(Request.Method.POST, App_Config.county_22,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing22);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout22 = 90000; // 30 seconds. You can change it
        RetryPolicy policy22 = new DefaultRetryPolicy(socketTimeout22,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest22.setRetryPolicy(policy22);
        AppController.getInstance().addToRequestQueue(stringRequest22);


        StringRequest stringRequest23 = new StringRequest(Request.Method.POST, App_Config.county_23,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing23);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout23 = 90000; // 30 seconds. You can change it
        RetryPolicy policy23 = new DefaultRetryPolicy(socketTimeout23,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest23.setRetryPolicy(policy23);
        AppController.getInstance().addToRequestQueue(stringRequest23);


        StringRequest stringRequest24 = new StringRequest(Request.Method.POST, App_Config.county_24,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing24);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout24 = 90000; // 30 seconds. You can change it
        RetryPolicy policy24 = new DefaultRetryPolicy(socketTimeout24,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest24.setRetryPolicy(policy24);
        AppController.getInstance().addToRequestQueue(stringRequest24);


        StringRequest stringRequest25 = new StringRequest(Request.Method.POST, App_Config.county_25,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing25);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout25 = 90000; // 30 seconds. You can change it
        RetryPolicy policy25 = new DefaultRetryPolicy(socketTimeout25,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest25.setRetryPolicy(policy25);
        AppController.getInstance().addToRequestQueue(stringRequest25);


        StringRequest stringRequest26 = new StringRequest(Request.Method.POST, App_Config.county_26,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing26);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout26 = 90000; // 30 seconds. You can change it
        RetryPolicy policy26 = new DefaultRetryPolicy(socketTimeout26,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest26.setRetryPolicy(policy26);
        AppController.getInstance().addToRequestQueue(stringRequest26);


        StringRequest stringRequest27 = new StringRequest(Request.Method.POST, App_Config.county_27,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing27);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout27 = 90000; // 30 seconds. You can change it
        RetryPolicy policy27 = new DefaultRetryPolicy(socketTimeout27,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest27.setRetryPolicy(policy27);
        AppController.getInstance().addToRequestQueue(stringRequest27);


        StringRequest stringRequest28 = new StringRequest(Request.Method.POST, App_Config.county_28,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing28);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout28 = 90000; // 30 seconds. You can change it
        RetryPolicy policy28 = new DefaultRetryPolicy(socketTimeout28,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest28.setRetryPolicy(policy28);
        AppController.getInstance().addToRequestQueue(stringRequest28);


        StringRequest stringRequest29 = new StringRequest(Request.Method.POST, App_Config.county_29,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing29);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout29 = 90000; // 30 seconds. You can change it
        RetryPolicy policy29 = new DefaultRetryPolicy(socketTimeout29,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest29.setRetryPolicy(policy29);
        AppController.getInstance().addToRequestQueue(stringRequest29);


        StringRequest stringRequest31 = new StringRequest(Request.Method.POST, App_Config.county_31,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing31);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout31 = 90000; // 30 seconds. You can change it
        RetryPolicy policy31 = new DefaultRetryPolicy(socketTimeout31,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest31.setRetryPolicy(policy31);
        AppController.getInstance().addToRequestQueue(stringRequest31);


        StringRequest stringRequest32 = new StringRequest(Request.Method.POST, App_Config.county_32,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing32);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout32 = 90000; // 30 seconds. You can change it
        RetryPolicy policy32 = new DefaultRetryPolicy(socketTimeout32,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest32.setRetryPolicy(policy32);
        AppController.getInstance().addToRequestQueue(stringRequest32);


        StringRequest stringRequest33 = new StringRequest(Request.Method.POST, App_Config.county_33,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing33);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout33 = 90000; // 30 seconds. You can change it
        RetryPolicy policy33 = new DefaultRetryPolicy(socketTimeout33,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest33.setRetryPolicy(policy33);
        AppController.getInstance().addToRequestQueue(stringRequest33);


        StringRequest stringRequest34 = new StringRequest(Request.Method.POST, App_Config.county_34,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing34);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout34 = 90000; // 30 seconds. You can change it
        RetryPolicy policy34 = new DefaultRetryPolicy(socketTimeout34,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest34.setRetryPolicy(policy34);
        AppController.getInstance().addToRequestQueue(stringRequest34);


        StringRequest stringRequest35 = new StringRequest(Request.Method.POST, App_Config.county_35,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing35);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout35 = 90000; // 30 seconds. You can change it
        RetryPolicy policy35 = new DefaultRetryPolicy(socketTimeout35,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest35.setRetryPolicy(policy35);
        AppController.getInstance().addToRequestQueue(stringRequest35);


        StringRequest stringRequest36 = new StringRequest(Request.Method.POST, App_Config.county_36,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing36);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout36 = 90000; // 30 seconds. You can change it
        RetryPolicy policy36 = new DefaultRetryPolicy(socketTimeout36,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest36.setRetryPolicy(policy36);
        AppController.getInstance().addToRequestQueue(stringRequest36);


        StringRequest stringRequest37 = new StringRequest(Request.Method.POST, App_Config.county_37,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing37);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout37 = 90000; // 30 seconds. You can change it
        RetryPolicy policy37 = new DefaultRetryPolicy(socketTimeout37,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest37.setRetryPolicy(policy37);
        AppController.getInstance().addToRequestQueue(stringRequest37);


        StringRequest stringRequest38 = new StringRequest(Request.Method.POST, App_Config.county_38,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing38);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout38 = 90000; // 30 seconds. You can change it
        RetryPolicy policy38 = new DefaultRetryPolicy(socketTimeout38,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest38.setRetryPolicy(policy38);
        AppController.getInstance().addToRequestQueue(stringRequest38);


        StringRequest stringRequest39 = new StringRequest(Request.Method.POST, App_Config.county_39,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing39);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout39 = 90000; // 30 seconds. You can change it
        RetryPolicy policy39 = new DefaultRetryPolicy(socketTimeout39,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest39.setRetryPolicy(policy39);
        AppController.getInstance().addToRequestQueue(stringRequest39);


        StringRequest stringRequest40 = new StringRequest(Request.Method.POST, App_Config.county_40,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing40);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout40 = 90000; // 30 seconds. You can change it
        RetryPolicy policy40 = new DefaultRetryPolicy(socketTimeout40,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest40.setRetryPolicy(policy40);
        AppController.getInstance().addToRequestQueue(stringRequest40);


        StringRequest stringRequest41 = new StringRequest(Request.Method.POST, App_Config.county_41,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing41);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout41 = 90000; // 30 seconds. You can change it
        RetryPolicy policy41 = new DefaultRetryPolicy(socketTimeout41,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest41.setRetryPolicy(policy41);
        AppController.getInstance().addToRequestQueue(stringRequest41);


        StringRequest stringRequest42 = new StringRequest(Request.Method.POST, App_Config.county_42,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing42);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout42 = 90000; // 30 seconds. You can change it
        RetryPolicy policy42 = new DefaultRetryPolicy(socketTimeout42,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest42.setRetryPolicy(policy42);
        AppController.getInstance().addToRequestQueue(stringRequest42);


        StringRequest stringRequest43 = new StringRequest(Request.Method.POST, App_Config.county_43,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing43);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout43 = 90000; // 30 seconds. You can change it
        RetryPolicy policy43 = new DefaultRetryPolicy(socketTimeout43,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest43.setRetryPolicy(policy43);
        AppController.getInstance().addToRequestQueue(stringRequest43);


        StringRequest stringRequest44 = new StringRequest(Request.Method.POST, App_Config.county_44,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing44);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout44 = 90000; // 30 seconds. You can change it
        RetryPolicy policy44 = new DefaultRetryPolicy(socketTimeout44,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest44.setRetryPolicy(policy44);
        AppController.getInstance().addToRequestQueue(stringRequest44);


        StringRequest stringRequest45 = new StringRequest(Request.Method.POST, App_Config.county_45,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing45);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout45 = 90000; // 30 seconds. You can change it
        RetryPolicy policy45 = new DefaultRetryPolicy(socketTimeout45,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest45.setRetryPolicy(policy45);
        AppController.getInstance().addToRequestQueue(stringRequest45);

        StringRequest stringRequest46 = new StringRequest(Request.Method.POST, App_Config.county_46,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing46);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout46 = 90000; // 30 seconds. You can change it
        RetryPolicy policy46 = new DefaultRetryPolicy(socketTimeout46,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest46.setRetryPolicy(policy46);
        AppController.getInstance().addToRequestQueue(stringRequest46);


        StringRequest stringRequest47 = new StringRequest(Request.Method.POST, App_Config.county_47,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing47);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout47 = 90000; // 30 seconds. You can change it
        RetryPolicy policy47 = new DefaultRetryPolicy(socketTimeout47,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest47.setRetryPolicy(policy47);
        AppController.getInstance().addToRequestQueue(stringRequest47);

        StringRequest stringRequest30 = new StringRequest(Request.Method.POST, App_Config.county_30,
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
                            Toast.makeText(ManuByCounty.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
                        // Toast.makeText(ManuByRegion.this, "Total number of Items are:" + listView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

                        TextView getTotalCount = (TextView) findViewById(R.id.testing30);
                        getTotalCount.setText("" + listView.getCount());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(ManuByCounty.this, error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuByCounty.this);

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };


        int socketTimeout30 = 90000; // 30 seconds. You can change it
        RetryPolicy policy30 = new DefaultRetryPolicy(socketTimeout30,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest30.setRetryPolicy(policy30);
        AppController.getInstance().addToRequestQueue(stringRequest30);

    }

    public void card_view1(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyOne.class);
        startActivity(intent1);
    }

    public void card_view2(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyTwo.class);
        startActivity(intent1);
    }

    public void card_view3(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyThree.class);
        startActivity(intent1);
    }

    public void card_view4(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyFour.class);
        startActivity(intent1);
    }

    public void card_view5(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyFive.class);
        startActivity(intent1);
    }

    public void card_view6(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountySix.class);
        startActivity(intent1);
    }

    public void card_view7(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountySeven.class);
        startActivity(intent1);
    }

    public void card_view8(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyEight.class);
        startActivity(intent1);
    }

    public void card_view9(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyNine.class);
        startActivity(intent1);
    }

    public void card_view10(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyTen.class);
        startActivity(intent1);
    }

    public void card_view11(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyEleven.class);
        startActivity(intent1);
    }

    public void card_view12(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyTwelve.class);
        startActivity(intent1);
    }

    public void card_view13(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyThirteen.class);
        startActivity(intent1);
    }

    public void card_view14(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyForteen.class);
        startActivity(intent1);
    }

    public void card_view15(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyFifteen.class);
        startActivity(intent1);
    }

    public void card_view16(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountySixteen.class);
        startActivity(intent1);
    }

    public void card_view17(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountySeventeen.class);
        startActivity(intent1);
    }

    public void card_view18(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyEighteen.class);
        startActivity(intent1);
    }

    public void card_view19(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyNineteen.class);
        startActivity(intent1);
    }

    public void card_view20(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyTwenty.class);
        startActivity(intent1);
    }

    public void card_view21(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyTwentyOne.class);
        startActivity(intent1);
    }

    public void card_view22(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyTwentyTwo.class);
        startActivity(intent1);
    }

    public void card_view23(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyTwentyThree.class);
        startActivity(intent1);
    }

    public void card_view24(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyTwentyFour.class);
        startActivity(intent1);
    }

    public void card_view25(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyTwentyFive.class);
        startActivity(intent1);
    }

    public void card_view26(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyTwentySix.class);
        startActivity(intent1);
    }

    public void card_view27(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyTwentySeven.class);
        startActivity(intent1);
    }

    public void card_view28(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyTwentyEight.class);
        startActivity(intent1);
    }

    public void card_view29(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyTwentyNine.class);
        startActivity(intent1);
    }

    public void card_view30(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyThirty.class);
        startActivity(intent1);
    }

    public void card_view31(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyThirtyOne.class);
        startActivity(intent1);
    }

    public void card_view32(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyThirtyTwo.class);
        startActivity(intent1);
    }

    public void card_view33(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyThirtyThree.class);
        startActivity(intent1);
    }

    public void card_view34(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyThirtyFour.class);
        startActivity(intent1);
    }

    public void card_view35(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyThirtyFive.class);
        startActivity(intent1);
    }

    public void card_view36(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyThirtySix.class);
        startActivity(intent1);
    }

    public void card_view37(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyThirtySeven.class);
        startActivity(intent1);
    }

    public void card_view38(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyThirtyEight.class);
        startActivity(intent1);
    }

    public void card_view39(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyThirtyNine.class);
        startActivity(intent1);
    }

    public void card_view40(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyForty.class);
        startActivity(intent1);
    }

    public void card_view41(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyFortyOne.class);
        startActivity(intent1);
    }

    public void card_view42(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyFortyTwo.class);
        startActivity(intent1);
    }

    public void card_view43(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyFortyThree.class);
        startActivity(intent1);
    }

    public void card_view44(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyFortyFour.class);
        startActivity(intent1);
    }

    public void card_view45(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyFortyFive.class);
        startActivity(intent1);
    }

    public void card_view46(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyFortySix.class);
        startActivity(intent1);
    }

    public void card_view47(View v) {
        Intent intent1 = new Intent(getApplicationContext(), CountyFortySeven.class);
        startActivity(intent1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
       // search(searchView);

        return true;
    }
}
//
//    private void search(SearchView searchView) {
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
//                return false;
//            }
//        });
//
//    }
//
//
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
//
//        salesListr.clear();
//
//        salesListr.addAll(filteredstocklist);
//
//        adapter.notifyDataSetChanged();
//
//        return filteredstocklist;
//    }
//    public void fab(View view){
//        Intent intent5 = new Intent(getApplicationContext(), Manufacturers.class);
//        startActivity(intent5);
//    }


