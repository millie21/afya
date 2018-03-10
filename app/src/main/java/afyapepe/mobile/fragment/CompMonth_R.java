package afyapepe.mobile.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import afyapepe.mobile.R;
import afyapepe.mobile.activity.App_Config;
import afyapepe.mobile.activity.HttpHandler;
import afyapepe.mobile.activity.Manufacturers;
import afyapepe.mobile.activity.Stock;
import afyapepe.mobile.adapter.CompDoctorsTotal;
import afyapepe.mobile.adapter.SimpleCompeRegionAdapter;
import afyapepe.mobile.adapter.SimpleCompeSalesAdapter;
import afyapepe.mobile.app.AppController;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

import static afyapepe.mobile.app.AppController.TAG;

/**
 * Created by Millie Agallo on 10/19/2017.
 */

public class CompMonth_R extends Fragment {

    private List<Stock> sectorList = new ArrayList<Stock>();
    private List<Stock> sectorListt = new ArrayList<Stock>();
    private ListView listView;
    private ListView listViewb;
    private SimpleCompeRegionAdapter adapter;
    private CompDoctorsTotal adapterb;
    private SQLiteHandler db;
    private SessionManager session;
    private ProgressDialog pDialog;

    public CompMonth_R() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_comp_r, container, false);
        db = new SQLiteHandler(getActivity());

        session = new SessionManager(getActivity());

        //Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();

        String email = user.get("email");

        View empty = view.findViewById(R.id.list_empty);
        listView = (ListView) view.findViewById(R.id.listview1);
        listViewb = (ListView) view.findViewById(R.id.listview2);
        // TaskListView.setVisibility((adapter.isEmpty())?View.GONE:View.VISIBLE);
        listView.setEmptyView(empty);
        adapter = new SimpleCompeRegionAdapter(getActivity(), sectorList);
        adapterb = new CompDoctorsTotal(getActivity(),sectorListt);
        listView.setAdapter(adapter);
        listViewb.setAdapter(adapterb);

        pDialog = new ProgressDialog(getActivity());

        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, App_Config.comp_month_r_url,
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
                                stock.setCounty(jsonObject.getString("county"));
                                stock.setTotalq(jsonObject.getString("totalq"));
                                stock.setTotal(jsonObject.getString("total"));
                                //  inventory.setEntry_date(jsonObject.getString("entry_date"));

                                sectorList.add(stock);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                        }


//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapter.notifyDataSetChanged();
//                        TextView getTotalCount = (TextView) getView().findViewById(R.id.testing12);
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
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(getActivity());

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

        //fortots
        StringRequest stringRequestb = new StringRequest(Request.Method.POST, App_Config.compweek_d_tot,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        try {

                            JSONArray request = new JSONArray(response);
                            for (int i = 0; i < request.length(); i++) {

                                Stock stock = new Stock();
                                JSONObject jsonObject = null;
                                jsonObject = request.getJSONObject(i);

                                stock.setTotalqb(jsonObject.getString("totalqb"));


                                sectorListt.add(stock);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                        }


//                        Notifies the attached observers that the underlying data has been changed
//                                * and any View reflecting the data set should refresh itself.
                        adapterb.notifyDataSetChanged();
                        // TextView getTotalCount = (TextView) getView().findViewById(R.id.testing12);
                        //  getTotalCount.setText(""+listView.getCount());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                            pDialog = null;
                        }
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                db = new SQLiteHandler(getActivity());

                // Fetching user details from SQLite
                HashMap<String, String> user = db.getUserDetails();

                String email = user.get("email");
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequestb);


        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), Manufacturers.class);
                startActivity(intent);
            }
        });

        return view;

    }
}

