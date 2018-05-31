package masterung.androidthai.in.th.ticketservice.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import masterung.androidthai.in.th.ticketservice.R;
import masterung.androidthai.in.th.ticketservice.utility.MyAlertDialog;
import masterung.androidthai.in.th.ticketservice.utility.MyConstance;
import masterung.androidthai.in.th.ticketservice.utility.ReadAllData;
import masterung.androidthai.in.th.ticketservice.utility.WriteTicketRequest;

public class AddNewTicketFragment extends Fragment {

    private String idString, nameUserString,
            idServirityChooseString = "id", userAssignChooseString = "User",
            serialString, detailString, dueDateString;
    private String[] userAssignStrings, nameAnSurAssingStrings,
            idSeverityStrings, nameSeverityStrings;

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

//        Create Severity
        createSeverity();

//        Save Controller
        saveController();

//        Calender Controller
        calenderController();

    }   // Main Method

    private void calenderController() {

        CalendarView calendarView = getView().findViewById(R.id.calenderDueDate);

        final Calendar calendar = Calendar.getInstance();
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        dueDateString = dateFormat.format(calendar.getTime());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                //dueDateString = Integer.toString(dayOfMonth) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(year);

                calendar.set(year, month, dayOfMonth);
                dueDateString = dateFormat.format(calendar.getTime());


            }
        });


    }

    private void saveController() {

        Button button = getView().findViewById(R.id.btnSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText serialEditText = getView().findViewById(R.id.edtSerial);
                EditText detailEditText = getView().findViewById(R.id.edtDetail);

                serialString = serialEditText.getText().toString().trim();
                detailString = detailEditText.getText().toString().trim();
                MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());

                if (serialString.isEmpty() || detailString.isEmpty()) {
//                    Have Space
                    myAlertDialog.normalDialog(getString(R.string.title_have_space),
                            getString(R.string.message_have_space));
                } else if (userAssignChooseString.equals("User")) {
                    myAlertDialog.normalDialog("Non Choose Assign",
                            "Please Choose Assign");
                } else if (idServirityChooseString.equals("id")) {
                    myAlertDialog.normalDialog("Not Severity",
                            "Please Choose Severy");
                } else {
                    confirmValue();
                }

            }
        });


    }

    private void confirmValue() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_user);
        builder.setTitle("Please Confirm Value");
        builder.setMessage("Create by ==> " + nameUserString + "\n" +
                "Serial ==> " + serialString + "\n" +
                "Detail ==> " + detailString + "\n" +
                "Assign ==> " + userAssignChooseString + "\n" +
                "Severity ==> " + idServirityChooseString + "\n" +
                "DueDate ==> " + dueDateString);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                uploadValueToServer();
                dialog.dismiss();
            }
        });

        builder.show();

    }

    private void uploadValueToServer() {

        try {

            MyConstance myConstance = new MyConstance();
            WriteTicketRequest writeTicketRequest = new WriteTicketRequest(getActivity());
            writeTicketRequest.execute(nameUserString, serialString, detailString,
                    userAssignChooseString, idServirityChooseString, dueDateString,
                    myConstance.getUrlPostTicketRequest());
            String result = writeTicketRequest.get();
            Log.d("31MayV2", "Result ==> " + result);

            if (Boolean.parseBoolean(result)) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment,
                                BaseTicketFragment.baseTicketInstance(idString, nameUserString))
                        .commit();
                Toast.makeText(getActivity(), "Upload Success", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getActivity(), "Cannot Upload", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void createSeverity() {
        Spinner spinner = getView().findViewById(R.id.spinnerSeverity);
        try {

            MyConstance myConstance = new MyConstance();
            String urlString = myConstance.getUrlSererityString();
            ReadAllData readAllData = new ReadAllData(getActivity());
            readAllData.execute(urlString);
            String jsonString = readAllData.get();
            Log.d("31MayV1", "JSoN ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            ArrayList<String> idStringArrayList = new ArrayList<>();
            ArrayList<String> nameStringArrayList = new ArrayList<>();

            idStringArrayList.add(idServirityChooseString);
            nameStringArrayList.add("Please Choose Severity");

            for (int i = 0; i < jsonArray.length(); i += 1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                idStringArrayList.add(jsonObject.getString("id"));
                nameStringArrayList.add(jsonObject.getString("Severity"));

            }   // for

            idSeverityStrings = changeArrayListToArray(idStringArrayList);
            nameSeverityStrings = changeArrayListToArray(nameStringArrayList);

            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, nameSeverityStrings);
            spinner.setAdapter(stringArrayAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    idServirityChooseString = idSeverityStrings[position];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    idServirityChooseString = idSeverityStrings[0];
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

            userAssignStringArrayList.add(userAssignChooseString);
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

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    userAssignChooseString = userAssignStrings[position];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    userAssignChooseString = userAssignStrings[0];
                }
            });


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
