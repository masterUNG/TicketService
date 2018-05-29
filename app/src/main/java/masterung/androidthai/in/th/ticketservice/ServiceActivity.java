package masterung.androidthai.in.th.ticketservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ServiceActivity extends AppCompatActivity {

    private String idString, nameUserString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

//        GetValue FromIntent
        idString = getIntent().getStringExtra("id");
        nameUserString = getIntent().getStringExtra("Name");
        Log.d("29MayV1", "id ==> " + idString);
        Log.d("29MayV1", "Name ==> " + nameUserString);


    }   // Main Method

}   // Main Class
