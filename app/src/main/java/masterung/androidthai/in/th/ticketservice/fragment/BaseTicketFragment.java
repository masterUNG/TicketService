package masterung.androidthai.in.th.ticketservice.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import masterung.androidthai.in.th.ticketservice.R;
import masterung.androidthai.in.th.ticketservice.utility.MyConstance;

public class BaseTicketFragment extends Fragment {

    private String idString, nameUserString;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static BaseTicketFragment baseTicketInstance(String idString, String nameString) {

        BaseTicketFragment baseTicketFragment = new BaseTicketFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", idString);
        bundle.putString("Name", nameString);
        baseTicketFragment.setArguments(bundle);
        return baseTicketFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        GetValue Argument
        getValueArgument();

//        Create TabLayout
        createTabLayout();


    }   // Main Method

    private void createTabLayout() {

        MyConstance myConstance = new MyConstance();
        String[] strings = myConstance.getTitleTabLayout();
        tabLayout = getView().findViewById(R.id.tabLayoutTicket);
        for (int i=0; i<strings.length; i+=1) {
            tabLayout.addTab(tabLayout.newTab().setText(strings[i]));
        }   // for
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



    }

    private void getValueArgument() {
        idString = getArguments().getString("id");
        nameUserString = getArguments().getString("Name");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_ticket, container, false);
        return view;
    }
}
