package afyapepe.mobile.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import afyapepe.mobile.R;
import afyapepe.mobile.activity.MAnuTrendSubMonth;
import afyapepe.mobile.activity.ManuTrendSubToday;
import afyapepe.mobile.activity.ManuTrendSubWeek;
import afyapepe.mobile.activity.ManuTrendSubYear;
import afyapepe.mobile.activity.Manufacturers;


public class Substitutions extends Fragment {
    public Substitutions() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_substitutions, container, false);

        //first cardview today trends
        CardView x = (CardView) rootView.findViewById(R.id.card_view);
        x.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ManuTrendSubToday.class);
                startActivity(intent);

            }

        });

        //second cv. week trends
        CardView m = (CardView) rootView.findViewById(R.id.card_view2);
        m.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ManuTrendSubWeek.class);
                startActivity(intent);

            }

        });

        //cv for month
        CardView w = (CardView) rootView.findViewById(R.id.card_view3);
        w.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), MAnuTrendSubMonth.class);
                startActivity(intent);

            }

        });

        //second cv. year trends
        CardView y = (CardView) rootView.findViewById(R.id.card_view4);
        y.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ManuTrendSubYear.class);
                startActivity(intent);

            }

        });

        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), Manufacturers.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

}
