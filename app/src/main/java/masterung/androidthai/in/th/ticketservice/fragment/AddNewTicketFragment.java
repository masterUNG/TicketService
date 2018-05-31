package masterung.androidthai.in.th.ticketservice.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import masterung.androidthai.in.th.ticketservice.R;
import masterung.androidthai.in.th.ticketservice.utility.MyConstance;
import masterung.androidthai.in.th.ticketservice.utility.ReadAllData;

public class AddNewTicketFragment extends Fragment {

    private String idString, nameUserString;
    private String[] userAssignStrings, nameAnSurAssingStrings;

    public static AddNewTicketFragment addNewTicketInstance(String idString, String nameString) {

        AddNewTicketFragment addNewTicketFragment = new AddNewTicketFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", idString);
        bundle.putString("Name", nameString);
        addNewTicketFragment.setArguments(bundle);
        return addNewTicketFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getValueArgument();

//        Create Assign
        createAssign();

    }   // Main Method

    private void createAssign() {
        Spinner spinner = getView().findViewById(R.id.spinnerAssign);
        try {

            MyConstance myConstance = new MyConstance();
            String urlString = myConstance.getUrlAssignString();
            ReadAllData readAllData = new ReadAllData(getActivity());
            readAllData.execute(urlString);
            String jsonString = readAllData.get();
            Log.d("30MayV3", "JSoN ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            ArrayList<String> userAssignStringArrayList = new ArrayList<>();
            ArrayList<String> nameAnSurAssignStringArrayList = new ArrayList<>();

            userAssignStringArrayList.add("User");
            nameAnSurAssignStringArrayList.add("Please Choose Assign");

            for (int i = 0; i < jsonArray.length(); i += 1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                userAssignStringArrayList.add(jsonObject.getString("User"));
                nameAnSurAssignStringArrayList.add(jsonObject.getString("FirstName") + " " + jsonObject.getString("LastName"));

            }   // for

            Log.d("30MayV3", "User ==> " + userAssignStringArrayList.toString());
            Log.d("30MayV3", "Name and Sur ==> " + nameAnSurAssignStringArrayList.toString());

            userAssignStrings = changeArrayListToArray(userAssignStringArrayList);
            nameAnSurAssingStrings = changeArrayListToArray(nameAnSurAssignStringArrayList);

            for (int i = 0; i < userAssignStrings.length; i += 1) {
                Log.d("30MayV3", "userAssign[" + i + "] ==> " + userAssignStrings[i]);
                Log.d("30MayV3", "nameAnSurAssign[" + i + "] ==> " + nameAnSurAssingStrings[i]);
            }

            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, nameAnSurAssingStrings);
            spinner.setAdapter(stringArrayAdapter);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] changeArrayListToArray(ArrayList<String> stringArrayList) {

        String[] strings = new String[stringArrayList.size()];

        for (int i = 0; i < stringArrayList.size(); i += 1) {
            strings[i] = stringArrayList.get(i);
        }
        return strings;
    }

    private void getValueArgument() {
        idString = getArguments().getString("id");
        nameUserString = getArguments().getString("Name");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_ticket, container, false);
        return view;
    }
}
