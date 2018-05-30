package masterung.androidthai.in.th.ticketservice.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import masterung.androidthai.in.th.ticketservice.R;
import masterung.androidthai.in.th.ticketservice.utility.MyConstance;

public class NewItemFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create ListView
        createListView();

    }   // Main Method

    private void createListView() {
        ListView listView = getView().findViewById(R.id.listViewNewItem);

        try {

            MyConstance myConstance = new MyConstance();
            String urlJSON = myConstance.getUrlNewItemString();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newitem, container, false);
        return view;
    }
}
