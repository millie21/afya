package afyapepe.mobile.activity;

import afyapepe.mobile.R;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.methods.HttpGetHC4;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Contact_info extends Fragment {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private TextView age;
    private TextView agea;
    private TextView blood;
    private SQLiteHandler db;
    private TextView gender;
    private RecyclerView mRVFishPrice;
    private TextView name;
    private TextView pob;
    private SessionManager session;

    private class AsyncFetch extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        ProgressDialog pdLoading;
        URL url;

        private AsyncFetch() {
            this.pdLoading = new ProgressDialog(Contact_info.this.getContext());
            this.url = null;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.pdLoading.setMessage("\tLoading...");
            this.pdLoading.setCancelable(false);
            this.pdLoading.show();
        }

        protected String doInBackground(String... params) {
            try {
                this.url = new URL("http://afyapepe.com/afyapepe/join_test.php");
                try {
                    this.conn = (HttpURLConnection) this.url.openConnection();
                    this.conn.setReadTimeout(15000);
                    this.conn.setConnectTimeout(10000);
                    this.conn.setRequestMethod(HttpGetHC4.METHOD_NAME);
                    this.conn.setDoOutput(true);
                    String stringBuilder;
                    try {
                        if (this.conn.getResponseCode() == Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
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
                        this.conn.disconnect();
                        return stringBuilder;
                    } catch (IOException e) {
                        e.printStackTrace();
                        stringBuilder = e.toString();
                        return "";
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
        }

        protected void onPostExecute(String result) {
            this.pdLoading.dismiss();
            List<DataFish> data = new ArrayList();
            this.pdLoading.dismiss();
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    String fname = json_data.getString("firstname");
                    String sname = json_data.getString("secondName");
                    String ageee = json_data.getString("email");
                    String gendee = json_data.getString("msisdn");
                    if (gendee.equals("1")) {
                        gendee = "Male";
                    } else if (gendee.equals("2")) {
                        gendee = "Female";
                    }
                    String pobee = json_data.getString("pob");
                    String bloodee = json_data.getString("blood_type");
                    Contact_info.this.name.setText(": " + fname + " " + sname);
                    Contact_info.this.agea.setText(": " + ageee);
                    Contact_info.this.gender.setText(": " + gendee);
                    Contact_info.this.pob.setText(": " + pobee);
                    Contact_info.this.blood.setText(": " + bloodee);
                }
            } catch (JSONException e) {
                Toast.makeText(Contact_info.this.getContext(), "Serious Problem Occured", 0).show();
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_info, container, false);
        this.agea = (TextView) rootView.findViewById(R.id.agek);
        this.blood = (TextView) rootView.findViewById(R.id.bloodk);
        this.pob = (TextView) rootView.findViewById(R.id.placeofk);
        this.gender = (TextView) rootView.findViewById(R.id.textView13k);
        this.name = (TextView) rootView.findViewById(R.id.namek);
        new AsyncFetch().execute(new String[0]);
        return rootView;
    }
}
