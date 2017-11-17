package afyapepe.mobile.fragment;

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
import afyapepe.mobile.activity.HttpHandler;

/**
 * Created by Millie Agallo on 10/23/2017.
 */

public class CompWeek_D extends Fragment {

    AlertDialog.Builder builder;
    private ProgressDialog pDialog;
    private ListView lv;
    FloatingActionButton FAB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_sector_activity_d, container, false);

        return rootView;
    }

    private static String url = "http://192.168.2.196/afyapepe3/public/showmanucompetitiondoctorweek";

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

                        String name = obj.getString("name");
                        String totalq = obj.getString("totalq");
                        String total = obj.getString("total");



                        HashMap<String, String> live = new HashMap<>();


                        // live.put("dgno", String.valueOf(dgno));
                        // live.put("collection_id", String.valueOf(collection_id));
                        live.put("name", String.valueOf(name));
                        live.put("totalq", totalq);
                        live.put("total", total);

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

                    R.layout.list_compe_doctor, new String[]{"name","totalq","total"},
                    new int[]{R.id.tvposition, R.id.tvid, R.id.tvname2});


            lv.setAdapter(adapter);

        }

    }

}
