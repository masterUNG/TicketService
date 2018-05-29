package masterung.androidthai.in.th.ticketservice.utility;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TicketViewPagerAdapter extends FragmentStatePagerAdapter{

    private FragmentManager fragmentManager;
    private int tabLayoutAnInt;

    public TicketViewPagerAdapter(FragmentManager fragmentManager,
                                  int tabLayoutAnInt) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.tabLayoutAnInt = tabLayoutAnInt;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                break;
        }


        return null;
    }

    @Override
    public int getCount() {
        return tabLayoutAnInt;
    }
}
