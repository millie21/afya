package afyapepe.mobile.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import afyapepe.mobile.R;
import afyapepe.mobile.fragment.Company1;
import afyapepe.mobile.fragment.Company2;
import afyapepe.mobile.fragment.Company3;
import afyapepe.mobile.fragment.Company4;
import afyapepe.mobile.fragment.Company5;
import afyapepe.mobile.fragment.Company6;

/**
 * Created by Millie Agallo on 10/10/2017.
 */

public class ManuCompeSalesAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public ManuCompeSalesAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Company1();
        } else if (position == 1) {
            return new Company2();
        } else if (position == 2) {
            return new Company3();
        } else if (position == 3){
            return  new Company4();
//        } else if (position == 4){
//            return new Company5();
        } else{
            return new Company6();
        }

    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 5;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.fragment_manu_competiton_analysis_sales_1);
            case 1:
                return mContext.getString(R.string.fragment_manu_competiton_analysis_sales_2);
            case 2:
                return mContext.getString(R.string.fragment_manu_competiton_analysis_sales_3);
            case 3:
                return mContext.getString(R.string.fragment_manu_competiton_analysis_sales_4);
            case 4:
                return mContext.getString(R.string.fragment_manu_competiton_analysis_sales_5);
//            case 5:
//                return mContext.getString(R.string.fragment_manu_competiton_analysis_sales_6);
            default:
                return null;
        }
    }


}

