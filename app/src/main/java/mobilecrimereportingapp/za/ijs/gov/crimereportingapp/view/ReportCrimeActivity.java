package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

/**
 * Created by MaribolleR on 2017/07/01.
 * Purpose: Crime reporting activity for the Crime reporting app.
 */

public class ReportCrimeActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar Toolbar;

    private Spinner spinnerKnowOffender, spinnerInjuries, spinnerSceneItems, spinnerWeapons;
    private ArrayAdapter<CharSequence> spinnerKnowOffenderAdapter, spinnerInjuriesAdapter, spinnerSceneItemsAdapter, spinnerWeaponsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_crime);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Report Crime");

        setSupportActionBar(Toolbar);
        /*Back icon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);

        /*Starting code for the spinners*/
        spinnerKnowOffender = (Spinner) findViewById(R.id.spinnerKnowOffender);
        spinnerInjuries = (Spinner) findViewById(R.id.spinnerInjuries);
        spinnerSceneItems = (Spinner) findViewById(R.id.spinnerSceneItems);
        spinnerWeapons = (Spinner) findViewById(R.id.spinnerWeapons);

        /* Create ArrayAdapters using the string array and a default spinner layout*/
        spinnerKnowOffenderAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);
        spinnerInjuriesAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);
        spinnerSceneItemsAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);
        spinnerWeaponsAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);

        /* Specify the layout to use when the list of choices appears*/
        spinnerKnowOffenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInjuriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSceneItemsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWeaponsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /* Apply the adapter to the spinner*/
        spinnerKnowOffender.setAdapter(spinnerKnowOffenderAdapter);
        spinnerInjuries.setAdapter(spinnerInjuriesAdapter);
        spinnerSceneItems.setAdapter(spinnerSceneItemsAdapter);
        spinnerWeapons.setAdapter(spinnerWeaponsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            return true;
        } else if (id == R.id.notifications) {
            //startActivity(new Intent(this, NotificationsActivity.class));

        } else if (id == R.id.inbox) {
            /*Add some inbox code to redirect*/
        }

        return false;

    }
}
