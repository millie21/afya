package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.methods.HttpGetHC4;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import afyapepe.mobile.R;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

public class AllergyList extends Fragment {


    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private TextView alagytype;
    private TextView alagyname;
    private TextView alagydate;


    private class AsyncFetch extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        ProgressDialog pdLoading;
        URL url;
        //URL urlfoop;


        private AsyncFetch() {
            this.pdLoading = new ProgressDialog(AllergyList.this.getContext());
            this.url = null;
            //this.urlfoop = null;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.pdLoading.setMessage("\tLoading Allergy list");
            this.pdLoading.setCancelable(false);
            this.pdLoading.show();
        }

        protected String doInBackground(String... params) {
            try {
                this.url = new URL("http://afyapepe.com/afyapepe/allergies.php");

                //this.urlfoop = new URL("http://afyapepe.com/afyapepe/allergiesfoop.php");
                try {
                    this.conn = (HttpURLConnection) this.url.openConnection();
                    //this.conn = (HttpURLConnection) this.urlfoop.openConnection();
                    this.conn.setReadTimeout(15000);
                    this.conn.setConnectTimeout(10000);
                    this.conn.setRequestMethod(HttpGetHC4.METHOD_NAME);
                    this.conn.setDoOutput(true);
                    String stringBuilder;
                    try {
                        if (this.conn.getResponseCode() == ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(this.conn.getInputStream()));
                            StringBuilder result = new StringBuilder();
                            while (true) {
                                String line = reader.readLine();
                                if (line == null) {
                                    break;
                                }
                                result.append(line);
                            }
                            stringBuilder = result.toString();
                            return stringBuilder;
                        }
                        stringBuilder = "unsuccessful";
                        this.conn.disconnect();
                        return stringBuilder;
                    } catch (IOException e) {
                        e.printStackTrace();
                        stringBuilder = e.toString();
                    } finally {
                        this.conn.disconnect();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                    return e1.toString();
                }
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
                return e2.toString();
            }
            return "";
        }

        protected void onPostExecute(String result) {
            this.pdLoading.dismiss();
            List<DataFish> data = new ArrayList();
            this.pdLoading.dismiss();
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);

                    String type = json_data.getString("type");
                    String datei = json_data.getString("date");
                    String allergy = json_data.getString("allergy");
                    /*String gendee = json_data.getString("gender");
                    if (gendee.equals("1")) {
                        gendee = "Male";
                    } else if (gendee.equals("2")) {
                        gendee = "Female";
                    }

                    */
                    String pobee = json_data.getString("pob");
                    String bloodee = json_data.getString("blood_type");
                    AllergyList.this.alagytype.setText(" "+type);
                    AllergyList.this.alagyname.setText(" "+allergy);
                    AllergyList.this.alagydate.setText(" "+datei);

                }
            } catch (JSONException e) {
                Toast.makeText(getContext(), "Serious Problem Occured", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_allergy_list, container, false);
        this.alagytype = (TextView) rootView.findViewById(R.id.alagytype);
        this.alagyname = (TextView) rootView.findViewById(R.id.alagyname);
        this.alagydate = (TextView) rootView.findViewById(R.id.alagydate);

        new AllergyList.AsyncFetch().execute(new String[0]);
        return rootView;
    }


}
