package afyapepe.mobile.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import afyapepe.mobile.R;

/**
 * Created by Millie Agallo on 10/5/2017.
 */

public class ManuDrugSubadapter extends FragmentPagerAdapter {

    private Context mContext;

    public ManuDrugSubadapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ManuDSubAway();
        } else if (position == 1) {
            return new ManuDSubIn();
        } else {
            return new ManuDSubTo();
        }

    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.fragment_manu_drug_substitutions_away);
            case 1:
                //from others to this manufacturer
                return mContext.getString(R.string.fragment_manu_drug_substitutions_in);
            case 2:
                return mContext.getString(R.string.fragment_manu_drug_substitutions_to);
            default:
                return null;
        }
    }


}

