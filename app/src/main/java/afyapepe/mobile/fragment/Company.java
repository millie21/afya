package afyapepe.mobile.fragment;
//afyapepe.mobile.activity.ManuTrendsView

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
import android.widget.ImageButton;

import afyapepe.mobile.R;
import afyapepe.mobile.activity.MAnuTrendWeekCo;
import afyapepe.mobile.activity.ManuTrendMonthCo;
import afyapepe.mobile.activity.ManuTrendTodayCo;
import afyapepe.mobile.activity.ManuTrendYearCo;
import afyapepe.mobile.activity.Manufacturers;


public class Company extends Fragment {

    CardView trendtoday;
    public Company() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);

        //first cardview today trends
        CardView b = (CardView) rootView.findViewById(R.id.card_view);
        b.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ManuTrendTodayCo.class);
                startActivity(intent);

            }

        });

        //set onclick for images too

        ImageButton wm = (ImageButton)rootView.findViewById(R.id.btLeft);
        wm.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(),ManuTrendTodayCo.class);
            }
        });


        //second cv. month trends
        CardView m = (CardView) rootView.findViewById(R.id.card_view3);
        m.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ManuTrendMonthCo.class);
                startActivity(intent);

            }

        });

        ImageButton wm3 = (ImageButton)rootView.findViewById(R.id.btLeft2);
        wm.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(),ManuTrendMonthCo.class);
            }
        });



        //cv for week
        CardView w = (CardView) rootView.findViewById(R.id.card_view2);
        w.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), MAnuTrendWeekCo.class);
                startActivity(intent);

            }

        });

        ImageButton wm4 = (ImageButton)rootView.findViewById(R.id.btRight);
        wm.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(),MAnuTrendWeekCo.class);
            }
        });

        //second cv. month trends
        CardView y = (CardView) rootView.findViewById(R.id.card_view4);
        y.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), ManuTrendYearCo.class);
                startActivity(intent);

            }

        });

        ImageButton wm2 = (ImageButton)rootView.findViewById(R.id.btLeft2);
        wm.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(),ManuTrendYearCo.class);
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

