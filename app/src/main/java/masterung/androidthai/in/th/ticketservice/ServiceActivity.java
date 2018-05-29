package masterung.androidthai.in.th.ticketservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import masterung.androidthai.in.th.ticketservice.fragment.BaseTicketFragment;
import masterung.androidthai.in.th.ticketservice.utility.ListViewAdapter;
import masterung.androidthai.in.th.ticketservice.utility.MyConstance;

public class ServiceActivity extends AppCompatActivity {

    private String idString, nameUserString;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

//        GetValue FromIntent
        getValueFromIntent();

//        Create Toolbar
        createToolbar();

//        Create ListView
        createListView();

//        Add Fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentServiceFragment,
                            BaseTicketFragment.baseTicketInstance(idString, nameUserString))
                    .commit();
        }

    }   // Main Method

    private void createListView() {

        ListView listView = findViewById(R.id.myListview);
        MyConstance myConstance = new MyConstance();

        String[] titleStrings = myConstance.getTitleListStrings();
        int[] iconInts = myConstance.getIconInts();
        ListViewAdapter listViewAdapter = new ListViewAdapter(ServiceActivity.this,
                titleStrings, iconInts);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                drawerLayout.closeDrawers();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_service, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        For Hamburger Icon
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

//        For SignOut
        if (item.getItemId() == R.id.itemSignOut) {
            signOut();
        }


        return super.onOptionsItemSelected(item);
    }

    private void signOut() {

        SharedPreferences sharedPreferences = getSharedPreferences("SkyUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", "");
        editor.commit();

        startActivity(new Intent(ServiceActivity.this, MainActivity.class));
        finish();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        actionBarDrawerToggle.onConfigurationChanged(newConfig);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    private void createToolbar() {

        toolbar = findViewById(R.id.toolbarService);
        setSupportActionBar(toolbar);


//        Setup Title
        getSupportActionBar().setTitle("Dash Board");
        getSupportActionBar().setSubtitle(nameUserString + " Login");

        drawerLayout = findViewById(R.id.drawerLayoutService);
        actionBarDrawerToggle = new ActionBarDrawerToggle(ServiceActivity.this,
                drawerLayout, R.string.open, R.string.close);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void getValueFromIntent() {
        idString = getIntent().getStringExtra("id");
        nameUserString = getIntent().getStringExtra("Name");
        Log.d("29MayV1", "id ==> " + idString);
        Log.d("29MayV1", "Name ==> " + nameUserString);
    }

}   // Main Class
