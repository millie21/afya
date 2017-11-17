package afyapepe.mobile.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import afyapepe.mobile.R;
import afyapepe.mobile.activity.SubMaxFT;
import afyapepe.mobile.activity.SubMonthFT;
import afyapepe.mobile.activity.SubTodayFT;
import afyapepe.mobile.activity.SubWeekFT;
import afyapepe.mobile.activity.SubYearFT;

public class ManuDSubIn extends Fragment {


    AlertDialog.Builder builder;
    private ProgressDialog pDialog;
    private ListView lv;
    FloatingActionButton FAB;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_manu_dsub_in, container, false);

        //first cardview today ddrug sub away
        CardView b = (CardView) rootView.findViewById(R.id.card_view);
        b.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), SubTodayFT.class);
                startActivity(intent);

            }

        });

        //second cv. month ddrug sub away
        CardView m = (CardView) rootView.findViewById(R.id.card_view2);
        m.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), SubWeekFT.class);
                startActivity(intent);

            }

        });

        //cv for week ddrug sub away
        CardView w = (CardView) rootView.findViewById(R.id.card_view3);
        w.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), SubMonthFT.class);
                startActivity(intent);

            }

        });

        //second cv. month ddrug sub away
        CardView y = (CardView) rootView.findViewById(R.id.card_view4);
        y.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), SubYearFT.class);
                startActivity(intent);

            }

        });

        //second cv. max ddrug sub away
        CardView x = (CardView) rootView.findViewById(R.id.card_view5);
        x.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), SubMaxFT.class);
                startActivity(intent);

            }

        });

        //second cv. custom ddrug sub away
        CardView z = (CardView) rootView.findViewById(R.id.card_view6);
        z.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(getActivity(),"Almost done...",Toast.LENGTH_LONG).show();

            }

        });

        return rootView;
    }

}

