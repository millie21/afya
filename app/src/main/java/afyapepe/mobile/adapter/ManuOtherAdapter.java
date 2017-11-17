package afyapepe.mobile.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import afyapepe.mobile.R;
import afyapepe.mobile.fragment.Sector_Company;
import afyapepe.mobile.fragment.Sector_Drug;
import afyapepe.mobile.fragment.Substitutions;

/**
 * Created by Millie Agallo on 10/4/2017.
 */



public class ManuOtherAdapter extends FragmentPagerAdapter {

    private Context mContext;
    Drawable myDrawable;


    public ManuOtherAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;

    }



    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Sector_Company();
        } else if (position == 1){
            return new Sector_Drug();

            }
        else {
            return new Substitutions();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 2;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
               // myDrawable = mContext.getResources().getDrawable(R.drawable.earth);


                return mContext.getString(R.string.fragment_companys);
            case 1:
               // myDrawable = mContext.getResources().getDrawable(R.drawable.stethoscope);
                return mContext.getString(R.string.fragment_drugs);

            default:
                return null;
        }



        }
    }


