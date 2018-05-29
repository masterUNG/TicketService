package masterung.androidthai.in.th.ticketservice.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import masterung.androidthai.in.th.ticketservice.MainActivity;
import masterung.androidthai.in.th.ticketservice.R;
import masterung.androidthai.in.th.ticketservice.ServiceActivity;
import masterung.androidthai.in.th.ticketservice.utility.MyAlertDialog;
import masterung.androidthai.in.th.ticketservice.utility.MyConstance;
import masterung.androidthai.in.th.ticketservice.utility.ReadAllData;

public class MainFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Check SharePrefer
        checkSharePrefer();

//        Create Toolbar
        createToolbar();

//        Submit Controller
        submitController();

    }   // Main Method

    private void checkSharePrefer() {

        try {

            SharedPreferences sharedPreferences = getActivity()
                    .getSharedPreferences("SkyUser", Context.MODE_PRIVATE);
            String idString = sharedPreferences.getString("id", "");
            String nameString = sharedPreferences.getString("Name", "");

            if (idString.length() != 0) {
                intentToService(nameString, idString);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void submitController() {
        Button button = getView().findViewById(R.id.btnSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

//                Check Space
                if (userString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                    myAlertDialog.normalDialog(getString(R.string.title_have_space),
                            getString(R.string.message_have_space));

                } else {
//                    No Space
                    try {

                        MyConstance myConstance = new MyConstance();
                        boolean userBool = true;
                        String truePasswordString = null, nameString = null, idString = null;

                        String urlJSON = myConstance.getUrlGetAllUser();

                        ReadAllData readAllData = new ReadAllData(getActivity());
                        readAllData.execute(urlJSON);
                        MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());

                        String resultJSON = readAllData.get();
                        Log.d("28MayV1", "JSON ==> " + resultJSON);

                        JSONArray jsonArray = new JSONArray(resultJSON);
                        for (int i=0; i<jsonArray.length(); i+=1) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            if (userString.equals(jsonObject.getString("User"))) {

                                userBool = false;
                                truePasswordString = jsonObject.getString("Password");
                                nameString = jsonObject.getString("Name");
                                idString = jsonObject.getString("id");

                            }

                        }

                        if (userBool) {
//                            User False
                            myAlertDialog.normalDialog("User False",
                                    "No This User in my Database");

                        } else if (passwordString.equals(truePasswordString)) {
                            Toast.makeText(getActivity(), "Welcome " + nameString,
                                    Toast.LENGTH_SHORT).show();

//                            Check Remember
                            CheckBox checkBox = getView().findViewById(R.id.chbRemember);
                            if (checkBox.isChecked()) {
                                saveIdAndName(idString, nameString);
                            }


//                            Intent to ServiceActivity
                            intentToService(nameString, idString);

                        } else {
                            myAlertDialog.normalDialog("Password False",
                                    "Please Try Again Password False");
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }   // if



            }   // onClick
        });
    }

    private void saveIdAndName(String idString, String nameString) {

        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences("SkyUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", idString);
        editor.putString("Name", nameString);
        editor.commit();

    }

    private void intentToService(String nameString, String idString) {
        Intent intent = new Intent(getActivity(), ServiceActivity.class);
        intent.putExtra("id", idString);
        intent.putExtra("Name", nameString);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itmeSetting) {

//            Replace Fragment
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentMainFragment, new SettingFragment())
                    .addToBackStack(null)
                    .commit();


            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_main, menu);

    }

    private void createToolbar() {

        Toolbar toolbar = getView().findViewById(R.id.toolbarMain);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

//        Setup Title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Welcome");

        setHasOptionsMenu(true);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}
