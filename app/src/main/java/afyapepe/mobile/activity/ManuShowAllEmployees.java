package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import afyapepe.mobile.R;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

public class ManuShowAllEmployees extends Fragment {


    AlertDialog.Builder builder;
    private ProgressDialog pDialog;
    private ListView lv;
    FloatingActionButton FAB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_manu_show_all_employees, container, false);

//        FloatingActionButton b = (FloatingActionButton) rootView.findViewById(R.id.fab);
//        b.setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(getActivity(), ManuAddEmployee.class);
//                startActivity(intent);
//
//            }
//
//        });
        return rootView;
    }

    private static String url = "http://192.168.2.199/afyapepe3/public/showmanuemployees?email=manu1@afyapepe.com";

    ArrayList<HashMap<String, String>> allemployeeslist;
    @Override
    public void onStart() {
        super.onStart();
        // initControls();
        // setContentView(R.layout.activity_sector_activity);

        builder = new AlertDialog.Builder(getActivity());
        allemployeeslist = new ArrayList<>();
        lv = (ListView) getView().findViewById(R.id.listview11);

        new GetAllJobs().execute();
    }

    private class GetAllJobs extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray joblists = new JSONArray(jsonStr);


                    // Getting JSON Array node
                    //JSONArray joblists = jsonObj.getJSONArray("alljobsdetails");

                    // looping through All Contacts
                    for (int i = 0; i < joblists.length(); i++) {
                        JSONObject obj = joblists.getJSONObject(i);

                        String id = obj.getString("id");
                        String name = obj.getString("name");
                        String role = obj.getString("role");
                        String email = obj.getString("email");
                        String job = obj.getString("job");
                        String region = obj.getString("region");


                        HashMap<String, String> live = new HashMap<>();


                        // live.put("dgno", String.valueOf(dgno));
                        // live.put("collection_id", String.valueOf(collection_id));
                        live.put("id", String.valueOf(id));
                        live.put("name", name);
                        live.put("role", role);
                        live.put("email",email);
                        live.put("job",job);
                        live.put("region",region);

                        // adding contact to adverts list
                        allemployeeslist.add(live);
                    }
                } catch (final JSONException e) {

                    Toast.makeText(getActivity().getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();


                }
            } else {
                Toast.makeText(getActivity().getApplicationContext(),
                                "Couldn't get json from server. Check Internet connection!",
                                Toast.LENGTH_LONG)
                                .show();
                            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();
            //0739077968

            ListAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(),

                    allemployeeslist,

                    R.layout.list_employees, new String[]{"id","name","role","email","job","region"},
                    new int[]{R.id.tvposition, R.id.tvid, R.id.tvname2, R.id.tvpos,R.id.tvname, R.id.tvname3});


            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id1) {
                    Intent breed =  new Intent(getActivity(),ManuShowSingleEmployeeActivity.class);

                    String id = ((TextView) view.findViewById(R.id.tvposition)).getText().toString();
                    String name = ((TextView) view.findViewById(R.id.tvid)).getText().toString();
                    String role = ((TextView) view.findViewById(R.id.tvpos)).getText().toString();
                    String email = ((TextView) view.findViewById(R.id.tvname)).getText().toString();
                    String job = ((TextView) view.findViewById(R.id.tvname2)).getText().toString();
                    String region = ((TextView) view.findViewById(R.id.tvname3)).getText().toString();
                //    String btname = ((TextView) view.findViewById(R.id.tvname2)).getText().toString();
//                    String livename = lvltypeguess.getText().toString();

                    breed.putExtra("id",id);
                    breed.putExtra("name", name);
                    breed.putExtra("role", role);
                    breed.putExtra("email", email);
                    breed.putExtra("job", job);
                    breed.putExtra("region", region);

                   // breed.putExtra("email", email);

                    breed.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(breed);


                }
            });
        }

    }

}
