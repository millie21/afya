package afyapepe.mobile.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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

/**
 * Created by Agallo on 18-Sep-17.
 */

public class ManuAddEmployee extends Fragment {

    EditText etname, etemail, etpassword,etrole;
    Spinner etregion,etjob;
    Button RegisterEmployee;
    String name, email, password, role, region, job;
    AlertDialog.Builder builder;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_activity_manu_add_employee, container, false);
        RegisterEmployee = (Button) rootView.findViewById(R.id.buttonSubmit);

        RegisterEmployee.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
        {
                String function, role, name, email, password, job, region;


                role = etrole.getText().toString();
                name = etname.getText().toString();
                email = etemail.getText().toString();
                password = etpassword.getText().toString();
                job = etjob.getSelectedItem().toString();
                region = etregion.getSelectedItem().toString();

//removed role.equals("") ||
                if (name.equals("") || email.equals("") || password.equals("") || job.equals("") || region.equals("")) {
                    builder.setTitle("Error");
                    builder.setMessage("Please enter all fields");
                    builder.setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                    builder.create();
                    builder.show();
                } else {
                    function = "addemployee";
                    MAnuEAddBackground jaa = new MAnuEAddBackground(getActivity());
                    jaa.execute(function, name, role, email, password, job, region);

                    //finish();
                }
            }
        });
        return rootView;

    }

    ArrayList<HashMap<String, String>> alldrugsubawaylist;

    @Override
    public void onStart() {
        super.onStart();

        builder = new AlertDialog.Builder(getActivity());
        alldrugsubawaylist = new ArrayList<>();

        etname = (EditText) getView().findViewById(R.id.ename);
        etemail = (EditText) getView().findViewById(R.id.eemail);
        etjob = (Spinner) getView().findViewById(R.id.egroup);
        etpassword = (EditText) getView().findViewById(R.id.eid);
        etregion = (Spinner) getView().findViewById(R.id.eregion);
        etrole = (EditText) getView().findViewById(R.id.erole);
       // RegisterEmployee = (Button) getView().findViewById(R.id.buttonSubmit);
        //  ShowEmployee = (Button) getView().findViewById(R.id.buttonShow);

        //region names spinner
        //spinner options
        List<String> list = new ArrayList<String>();

        list.add("Mombasa");
        list.add("Kwale");
        list.add("Kilifi");
        list.add("Tana River");
        list.add("Lamu");
        list.add("Taita Taveta");
        list.add("Garissa");
        list.add("Wajir");
        list.add("Mandera");
        list.add("Marsabit");
        list.add("Isiolo");
        list.add("Meru");
        list.add("Tharaka Nithi");
        list.add("Embu");
        list.add("Kitui");
        list.add("Machakos");
        list.add("Makueni");
        list.add("Nyandarua");
        list.add("Nyeri");
        list.add("Kirinyaga");
        list.add("Murang'a");
        list.add("Kiambu");
        list.add("Turkana");
        list.add("West Pokot");
        list.add("Uasin Gishu");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        etregion.setAdapter(dataAdapter);
//job type spinner
        List<String> groups = new ArrayList<String>();
        groups.add("Manager");
        groups.add("Sales Representative");

        ArrayAdapter<String> groupAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, groups);

        groupAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);


        etjob.setAdapter(groupAdapter);
    }
            //dont delete
//        ShowEmployee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(getApplicationContext(),ManuShowAllEmployees.class);
//
//                startActivity(intent);
//
//            }
//        });
            //  new GetAllJobs().execute();

        }
