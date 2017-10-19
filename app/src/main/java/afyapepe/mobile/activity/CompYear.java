package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import afyapepe.mobile.R;

/**
 * Created by Millie Agallo on 10/10/2017.
 */

public class CompYear extends Fragment {


    AlertDialog.Builder builder;
    private ProgressDialog pDialog;
    private ListView lv;
    FloatingActionButton FAB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_sector_activity_d, container, false);
        return rootView;

    }

    private static String url = "http://192.168.2.191/afyapepe3/public/showmanusectorsummary?email=manu1@afyapepe.com";

    ArrayList<HashMap<String, String>> allcompelist;



    @Override
    public void onStart() {
        super.onStart();


        builder = new AlertDialog.Builder(getActivity());
        allcompelist = new ArrayList<>();
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

                        String name = obj.getString("name");
                        String drugname = obj.getString("drugname");
                        String id = obj.getString("id");
                        String total = obj.getString("total");

                        HashMap<String, String> live = new HashMap<>();

                        live.put("name", String.valueOf(name));
                        live.put("drugname", drugname);
                        live.put("id", id);
                        live.put("total", total);

                        // adding contact to adverts list
                        allcompelist.add(live);
                    }
                } catch (final JSONException e) {

                    Toast.makeText(getActivity().getApplicationContext(),
                            "Json parsing error: " + e.getMessage(),
                            Toast.LENGTH_LONG)
                            .show();

                }
            } else {

                Toast.makeText(getActivity().getApplicationContext(),
                        "No Data is available for viewing",
                        //"Couldn't get json from server. Check Internet connection!",
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

                    allcompelist,

                    R.layout.list_sector, new String[]{"name", "drugname", "id", "total"},
                    new int[]{R.id.tvposition, R.id.tvid, R.id.tvpos, R.id.tvid2});


            lv.setAdapter(adapter);
        }

    }


}
