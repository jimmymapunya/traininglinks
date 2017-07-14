package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

/**
 * Created by ThatoM on 2017/07/12.
 * This is the class for Individual feedback
 */

public class IndividualFeedbackActivity extends AppCompatActivity {

    private Toolbar Toolbar;
    private Spinner spinnerCaseNo;
    private Spinner spinnerActorRole;
    private Spinner spinnerActorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_feedback);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Individual Feedback");

        setSupportActionBar(Toolbar);
        /*Back icon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);

        addItemsOnSpinnerCaseNo();
        addItemsOnSpinnerActorRole();
        addItemsOnSpinnerActorName();
    }
    void addItemsOnSpinnerCaseNo(){

        spinnerCaseNo = (Spinner) findViewById(R.id.spinnerCaseNo);
        List<String> list = new ArrayList<String>();
        list.add("---Select Case No---");
        list.add("05/2017/99");
        list.add("07/2017/40");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, list);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerCaseNo.setAdapter(dataAdapter);
    }
    void addItemsOnSpinnerActorRole(){

        spinnerActorRole = (Spinner) findViewById(R.id.spinnerActorRole);
        List<String> list = new ArrayList<String>();
        list.add("---Select Role of Actor---");
        list.add("Police Officer");
        list.add("National Prosecuting Officer");
        list.add("Judge");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, list);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerActorRole.setAdapter(dataAdapter);

    }
    void addItemsOnSpinnerActorName(){

        spinnerActorName = (Spinner) findViewById(R.id.spinnerActorName);
        List<String> list = new ArrayList<String>();
        list.add("---Select Name of Actor---");
        list.add("The TF Mr. Makola Himself");
        list.add("Rappy");
        list.add("koco");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, list);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerActorName.setAdapter(dataAdapter);

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
