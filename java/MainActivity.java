package com.example.android.vikingtowingservice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the ListView resource.
        mainListView = (ListView) findViewById(R.id.main_list_view);

        // Create and populate a List of services.
        String[] services = new String[]{};

        ArrayList<String> serviceList = new ArrayList<String>();
        serviceList.addAll(Arrays.asList(services));

        // Create ArrayAdapter using the service list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.list_row, serviceList);

        // Add more services
        listAdapter.add("VIKING TRACTARI AUTO va ofera urmatoarele servicii:");
        listAdapter.add(" -- Repatrieri Auto");
        listAdapter.add(" -- Tractari-Transport auto intern si international");
        listAdapter.add(" -- Posibilitatea transportului a 2 masini simultan");
        listAdapter.add(" -- Asistenta rutiera ");
        listAdapter.add(" -- Depanari");
        listAdapter.add(" -- Remorcari");
        listAdapter.add(" -- Pene cauciuc");
        listAdapter.add(" -- Pene benzina");
        listAdapter.add(" -- Pene curent");
        listAdapter.add(" -- Punctualitate-seriozitate-profesionalism.\nLA CELE MAI BUNE PRETURI! NEGOCIABILE!");

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter(listAdapter);


        //create a new custom pager adapter
        CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //find and set an action for the floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0751927877"));
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_email) {
            // handle the email action
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto: lopos_marian@yahoo.com"));
            startActivity(Intent.createChooser(emailIntent, "Send feedback"));

        } else if (id == R.id.nav_phone) {
            //handle the call number action
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:0751927877"));
            startActivity(intent);

        } else if (id == R.id.nav_address) {
            //turn-by-turn navigation to Aurel Vlaicu, in Arad, Romania:
            Uri gmmIntentUri = Uri.parse("google.navigation:q=Aurel+Vlaicu,+Arad+Arad+County");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }

        } else if (id == R.id.nav_http) {
            //handle the new webpage action
            String url = "https://www.facebook.com/Tractari-auto-VIKING-154948577998423/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
