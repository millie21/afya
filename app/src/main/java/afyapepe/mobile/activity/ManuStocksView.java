package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import afyapepe.mobile.R;

import static afyapepe.mobile.R.id.fab;
//import static afyapepe.mobile.R.id.testing12;
import static afyapepe.mobile.app.AppController.TAG;

import afyapepe.mobile.adapter.SimpleStockAdapter;
import afyapepe.mobile.adapter.StockTotalAdapter;
import  afyapepe.mobile.app.AppController;
import afyapepe.mobile.fragment.ManuByDoctor;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;
//import static afyapepe.mobile.he;
/**
 * Created by Agallo on 18-Sep-17.
 */

public class ManuStocksView extends AppCompatActivity {

    ListView TaskListView;
    ListView listViewstockt;
    FloatingActionButton fab;
    ProgressBar progressBar;
    AlertDialog.Builder builder;
    private SQLiteHandler db;
    private SessionManager session;
    List<Stock> stocklist = new ArrayList<>();
    List<Stock> stockListtotal = new ArrayList<>();
    SimpleStockAdapter adapter;
    StockTotalAdapter stockTotalAdapter;
    TextView displayTextViewTitle;
    //TextView testing12;
    RetryPolicy setRetryPolicy;

    private ProgressDialog pDialog;

   // MaterialSearchView searchView;
    SearchView searchView;

    MenuItem searchMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manu_stocks_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new SQLiteHandler(ManuStocksView.this);

        session = new SessionManager(ManuStocksView.this);

        HashMap<String, String> user = db.getUserDetails();

        String email = user.get("email");

        View empty = findViewById(R.id.list_empty);
        TaskListView = (ListView) findViewById(R.id.listview11);
        TaskListView.setEmptyView(empty);

        listViewstockt = (ListView) findViewById(R.id.listview);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        adapter = new SimpleStockAdapter(ManuStocksView.this, stocklist);
        stockTotalAdapter = new StockTotalAdapter(ManuStocksView.this,stockListtotal);

        TaskListView.setAdapter(adapter);
        listViewstockt.setAdapter(stockTotalAdapter);
        // Showing progress dialog
        //progressBar = new ProgressBar(ManuStocksView.this);

        pDialog = new ProgressDialog(ManuStocksView.this);

        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();

        //for error code 500
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, App_Config.stocks_url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        //progressBar.dismiss();
                        try {
                            JSONArray request = new JSONArray(response);
                            for (int i = 0; i < request.length(); i++) {
                                Stock stock = new Stock();
                                JSONObject jsonObject = null;
                                jsonObject = request.getJSONObject(i);
                                stock.setDrugname(jsonObject.getString("drugname"));
                                stock.setName(jsonObject.getString("name"));
                                stock.setQuantity(jsonObject.getString("quantity"));

                                stocklist.add(stock);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuStocksView.this, e.toString(), Toast.LENGTH_LONG).show();
                        }

                        adapter.notifyDataSetChanged();
                      // Toast.makeText(getApplicationContext(), "Total number of Items are:" + TaskListView.getAdapter().getCount() , Toast.LENGTH_LONG).show();

//                        TextView getTotalCount = (TextView) findViewById(R.id.testing12);
//                        getTotalCount.setText(""+TaskListView.getCount());

                        pDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }
                Toast.makeText(ManuStocksView.this, error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuStocksView.this);

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

        //error 500
        params = new HashMap<String, String>();
        params.put("email", email);
        //calc for total

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, App_Config.stock_total_url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        //progressBar.dismiss();
                        try {
                            JSONArray request = new JSONArray(response);
                            for (int i = 0; i < request.length(); i++) {
                                Stock stock = new Stock();
                                JSONObject jsonObject = null;
                                jsonObject = request.getJSONObject(i);
                                stock.setTotalqb(jsonObject.getString("totalqb"));

                                stockListtotal.add(stock);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ManuStocksView.this, e.toString(), Toast.LENGTH_LONG).show();
                        }

                        stockTotalAdapter.notifyDataSetChanged();

                        pDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pDialog != null) {
                    pDialog.dismiss();
                    pDialog = null;
                }
                Toast.makeText(ManuStocksView.this, error.toString(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(ManuStocksView.this);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) MenuItemCompat.getActionView(search);
        search(searchView);


        return true;
    }

    private void search(SearchView searchView){
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
            filteredstocklist = stocklist;
        }
        else {
            ArrayList<Stock> filteredList = new ArrayList<>();

            for (Stock stock : stocklist){

                if(stock.getDrugname().toLowerCase().contains(charString.toLowerCase())){
                    filteredList.add(stock);
                }
            }
            filteredstocklist = filteredList;
        }

        stocklist.clear();

        stocklist.addAll(filteredstocklist);

        adapter.notifyDataSetChanged();

        return filteredstocklist;
    }
    public void fab(View view){
        Intent intent5 = new Intent(getApplicationContext(), Manufacturers.class);
        startActivity(intent5);
    }
}
