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
import afyapepe.mobile.activity.ManuTrendMonthD;
import afyapepe.mobile.activity.ManuTrendTodayD;
import afyapepe.mobile.activity.ManuTrendWeekD;
import afyapepe.mobile.activity.ManuTrendYearD;
import afyapepe.mobile.activity.Manufacturers;

public class Drug extends Fragment {
    public Drug() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drug, container, false);

        //first cardview today trends
        CardView b = (CardView) rootView.findViewById(R.id.card_view);
        b.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ManuTrendTodayD.class);
                startActivity(intent);

            }

        });

        //second cv. month trends
        CardView m = (CardView) rootView.findViewById(R.id.card_view3);
        m.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ManuTrendMonthD.class);
                startActivity(intent);

            }

        });

        //cv for week
        CardView w = (CardView) rootView.findViewById(R.id.card_view2);
        w.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ManuTrendWeekD.class);
                startActivity(intent);

            }

        });

        //second cv. month trends
        CardView y = (CardView) rootView.findViewById(R.id.card_view4);
        y.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ManuTrendYearD.class);
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
