package afyapepe.mobile.activity;



import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import afyapepe.mobile.R;
import afyapepe.mobile.helper.SQLiteHandler;
import afyapepe.mobile.helper.SessionManager;

import static afyapepe.mobile.R.drawable.style;

public class registactivtest extends AppCompatActivity {

    BufferedReader reader;
    BufferedReader reader2;
    BufferedReader reader3;
    BufferedReader reader4;
    private SQLiteHandler db;
    private SessionManager session;
    private TextView txtName;
    private TextView txtEmail;
    private TextView jsonresul;
    private TextView names;
    private TextView no;
    private TextView agen;
    private TextView joinda;
    private TextView tex;
    private TextView gendus;
    private JSONArray parentArray;
    private JSONObject parentJSON;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);
        Button btnHit = (Button) findViewById(R.id.btnHit);

        Button btnout = (Button) findViewById(R.id.btnLogout);


        //txtName = (TextView) findViewById(R.id.name);
        //txtEmail = (TextView) findViewById(R.id.email);
        Button acces = (Button) findViewById(R.id.navigate);

        jsonresul = (TextView) findViewById(R.id.jsonres);
        //names = (TextView) findViewById(R.id.names);
        //agen = (TextView) findViewById(R.id.ageu);
        //gendus = (TextView) findViewById(R.id.gendu);
        //joinda = (TextView) findViewById(R.id.jondu);
        //no = (TextView) findViewById(R.id.number);
        tex = (TextView) findViewById(R.id.tex);


        acces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Spinner blio = (Spinner) findViewById(R.id.spinneracces);

                Object tst = blio.getSelectedItem();
                String karma = tst.toString();

                if (karma.equals("All Patients")) {
                    startActivity(new Intent(registactivtest.this, registrarpage.class));

                } else if (karma.equals("Todays Patients")) {
                    startActivity(new Intent(registactivtest.this, apatient.class));

                } else if (karma.equals("Consultation Fee")) {
                    startActivity(new Intent(registactivtest.this, consulfee.class));

                }


            }
        });



















        db = new SQLiteHandler(getApplicationContext());


        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from SQLite
        //HashMap<String, String> user = db.getUserDetails();

        //String name = user.get("name");
        //String email = user.get("email");

        // Displaying the user details on the screen
        // txtName.setText(name);
        // txtEmail.setText(email);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        pDialog.setMessage("️ INFLATING ALL PATIENTS ️");
        showDialog();

        new JSONtask().execute("http://afyapepe.com/afyapepe/users.php");


        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pDialog = new ProgressDialog(registactivtest.this);
                pDialog.setCancelable(false);


                pDialog.setMessage("️ INFLATING ALL PATIENTS ️");
                showDialog();



                new JSONtask().execute("http://afyapepe.com/afyapepe/users.php");
            }
        });



      /*btnout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
           logoutUser();
          }
      });
      */


    }


    public class JSONtask extends AsyncTask<String, String, String> {

        String joindat;
        String age;
        String gend;
        JSONObject parentJSON;
        JSONArray parentArray;
        String finalJson;
        StringBuffer buffert;
        StringBuffer buffermu;
        StringBuffer buffermuc;
        StringBuffer buffermucu;
        StringBuffer buffermudec;
        StringBuffer foopa;
        StringBuffer finalBufferedString;
        StringBuffer bufnam;
        StringBuffer bufno;




        @Override
        protected String doInBackground(String... params) {



            HttpURLConnection connection = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);


                }


                //this is where the real stuff goes on lol...


                finalJson = buffer.toString();
                //The JSON starts with a curly bracket so we get the json object first
                //But I have to loop through because there are many objects in the list


                parentJSON = new JSONObject(finalJson);
                //Then the square brackets, where we now access it using jsonarray


                parentArray = parentJSON.getJSONArray("patients");


                finalBufferedString = new StringBuffer();
                buffert = new StringBuffer();
                buffermu = new StringBuffer();
                buffermuc = new StringBuffer();
                bufnam = new StringBuffer();
                bufno = new StringBuffer();



                for (int i = 0, j = 1; i < parentArray.length(); j++, i++) {
                    //In the jsonarray there are objects so we once again access them as I said above
                    JSONObject finalObject = parentArray.getJSONObject(i);


                    String firstname = finalObject.getString("firstname");

                    if (firstname == null) {
                        firstname = "No Data";

                    }


                    String secondName = finalObject.getString("secondName");

                    if (secondName == null) {
                        secondName = "No Data";

                    }
                    age = finalObject.getString("age");
                    if (age == null) {
                        secondName = "No Data";

                    }
                    joindat = finalObject.getString("created_at");
                    if (age == null) {
                        joindat = "No Data";

                    }
                    gend = finalObject.getString("gender");
                    if (gend.equals("1")) {
                        gend = "Male";
                    } else {
                        gend = "Female";
                    }








                    finalBufferedString.append("\n" + j+"." + ""+ firstname + " " + secondName+"\n"+"\n"+"\t"+"\t"+"\t"+"\t"+"Age: "+age+"\n"+"\t"+"\t"+"\t"+"\t"+"Gender: "+gend+"\n"+"\t"+"\t"+"\t"+"\t"+ "Join Date: "+joindat+"\n"+"\n"+"\n");

                    //foopa = buffert.append("\n" + "\n" + joindat);
                    //buffermudec = buffermu.append("\n" + "\n" + age);
                    //buffermucu = buffermuc.append("\n"+ "\n" + gend);


                    //no.setText(String.valueOf(d));


                }
                return finalBufferedString.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();


                }

                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            return null;


        }

        @Override
        protected void onPostExecute(String result) {

            hideDialog();
            String line=".                                                                  .";
            super.onPostExecute(result);
            String txting=result.toString();

            TextView txtinga=new TextView(registactivtest.this);






            //for (int v = 0; v < parentArray.length();  v++) {


            //joinda.setText(foopa);
            //agen.setText(buffermudec);
            //gendus.setText(buffermucu);
            //}


        }


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_refresh:
                // EITHER CALL THE METHOD HERE OR DO THE FUNCTION DIRECTLY
                logoutUser();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(registactivtest.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }



}










