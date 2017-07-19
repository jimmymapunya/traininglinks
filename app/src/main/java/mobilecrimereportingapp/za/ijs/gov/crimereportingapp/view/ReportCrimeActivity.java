package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

/**
 * Created by MaribolleR on 2017/07/01.
 * Purpose: Crime reporting activity for the Crime reporting app.
 */

public class ReportCrimeActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar Toolbar;
    private Context context = this;
    private static final String URL_case = "http://innovationmessagehub.azurewebsites.net//api/MessageHub/CreateCaseDetail";

    private Spinner spinnerKnowOffender, spinnerInjuries, spinnerSceneItems, spinnerWeapons, spinnerFirstAccount, spinnerWitnesses, spinnerRacialGroup,
            spinnerGender, spinnerAgeGroup, spinnerFacialIdentikit;

    private ArrayAdapter<CharSequence> spinnerKnowOffenderAdapter, spinnerInjuriesAdapter, spinnerSceneItemsAdapter, spinnerWeaponsAdapter, spinnerFirstAccountAdapter,
            spinnerWitnessesAdapter, spinnerRacialGroupAdapter, spinnerGenderAdapter, spinnerAgeGroupAdapter, spinnerFacialIdentikitAdapter;

    private Button btnAddOffenderInfo, btnAddWitnessInfo,btnSubmit;
    private EditText txtBeforeCrime, txtDuringCrime, txtAfterCrime, txtInjuries, txtCrimeSurroundings, txtOffenderName, txtOffenderContact, txtOffenderAddress, txtTattoos,
            txtAppearance, txtWitnessName, txtWitnessContact, txtWitnessAddress;
    private TextView notificationCountIcon, inboxCountIcon, lblWitnessName, lblWitnessContact, lblWitnessAddress;
    private LinearLayout OffenderDetailsLayout,doneWitnessDetails;
    private FrameLayout notificationLayout, inboxLayout;
    private CheckBox checkBoxConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_crime);

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);

        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        notificationCountIcon.setText(MainActivity.notificationCount);
        inboxCountIcon.setText(MainActivity.inboxCount);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);

        Toolbar.setTitle("Report Crime");

        setSupportActionBar(Toolbar);

        /*Back notificationicon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);

        /*EditText components initialisation*/
        txtBeforeCrime = (EditText) findViewById(R.id.txtBeforeCrime);
        txtDuringCrime = (EditText) findViewById(R.id.txtDuringCrime);
        txtAfterCrime = (EditText) findViewById(R.id.txtAfterCrime);
        txtInjuries = (EditText) findViewById(R.id.txtInjuries);
        txtCrimeSurroundings = (EditText) findViewById(R.id.txtCrimeSurroundings);
        txtOffenderName = (EditText) findViewById(R.id.txtOffenderName);
        txtOffenderContact = (EditText) findViewById(R.id.txtOffenderContact);
        txtOffenderAddress = (EditText) findViewById(R.id.txtOffenderAddress);
        txtTattoos = (EditText) findViewById(R.id.txtTattoos);
        txtAppearance = (EditText) findViewById(R.id.txtAppearance);
        txtWitnessName = (EditText) findViewById(R.id.txtWitnessName);
        txtWitnessContact = (EditText) findViewById(R.id.txtWitnessContact);
        txtWitnessAddress = (EditText) findViewById(R.id.txtWitnessAddress);

        /*TextView components initialisation*/


        /*Starting code for the spinners*/
        spinnerKnowOffender = (Spinner) findViewById(R.id.spinnerKnowOffender);
        spinnerInjuries = (Spinner) findViewById(R.id.spinnerInjuries);
        spinnerSceneItems = (Spinner) findViewById(R.id.spinnerSceneItems);
        spinnerWeapons = (Spinner) findViewById(R.id.spinnerObjectsUsed);
        spinnerFirstAccount = (Spinner) findViewById(R.id.spinnerFirstAccount);
        spinnerWitnesses = (Spinner) findViewById(R.id.spinnerWitnesses);

        spinnerRacialGroup = (Spinner) findViewById(R.id.spinnerRacialGroup);
        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        spinnerAgeGroup = (Spinner) findViewById(R.id.spinnerAgeGroup);
        spinnerFacialIdentikit = (Spinner) findViewById(R.id.spinnerFacialIdentikit);

        /* Create ArrayAdapters using the string array and a default spinner layout*/
        spinnerKnowOffenderAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);
        spinnerInjuriesAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);
        spinnerSceneItemsAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);
        spinnerWeaponsAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);
        spinnerFirstAccountAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);
        spinnerWitnessesAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);

        spinnerRacialGroupAdapter = ArrayAdapter.createFromResource(this,
                R.array.racial_group, android.R.layout.simple_spinner_item);
        spinnerGenderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        spinnerAgeGroupAdapter = ArrayAdapter.createFromResource(this,
                R.array.age_group, android.R.layout.simple_spinner_item);
        spinnerFacialIdentikitAdapter = ArrayAdapter.createFromResource(this,
                R.array.injuries_choice, android.R.layout.simple_spinner_item);

        /* Specify the layout to use when the list of choices appears*/
        spinnerKnowOffenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInjuriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSceneItemsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWeaponsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFirstAccountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWitnessesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerRacialGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAgeGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFacialIdentikitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /* Apply the adapter to the spinner*/
        spinnerKnowOffender.setAdapter(spinnerKnowOffenderAdapter);
        spinnerInjuries.setAdapter(spinnerInjuriesAdapter);
        spinnerSceneItems.setAdapter(spinnerSceneItemsAdapter);
        spinnerWeapons.setAdapter(spinnerWeaponsAdapter);
        spinnerFirstAccount.setAdapter(spinnerFirstAccountAdapter);
        spinnerWitnesses.setAdapter(spinnerWitnessesAdapter);

        spinnerRacialGroup.setAdapter(spinnerRacialGroupAdapter);
        spinnerGender.setAdapter(spinnerGenderAdapter);
        spinnerAgeGroup.setAdapter(spinnerAgeGroupAdapter);
        spinnerFacialIdentikit.setAdapter(spinnerFacialIdentikitAdapter);

        /*Dynamic linear layout offender and witness details*/
        OffenderDetailsLayout = (LinearLayout) findViewById(R.id.OffenderDetailsLayout);
        doneWitnessDetails = (LinearLayout) findViewById(R.id.doneWitnessDetails);

        /*Button to add the dynamic linear layout above*/
        btnAddOffenderInfo = (Button) findViewById(R.id.btnAddOffenderInfo);
        btnAddWitnessInfo = (Button) findViewById(R.id.btnAddWitnessInfo);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        /*Checkbox to confirm details reported are correct*/
        checkBoxConfirmation = (CheckBox) findViewById(R.id.checkBoxConfirmation);

        /*Button listeners*/
        btnAddOffenderInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = LayoutInflater.from(context).inflate(R.layout.custom_witness_done,null);
                OffenderDetailsLayout.addView(view);
            }
        });

        btnAddWitnessInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String manda = txtWitnessName.getText().toString();

                if(txtWitnessName.getText().toString() != null &&  txtWitnessAddress.getText().toString() != null  ){

                    final View view = LayoutInflater.from(context).inflate(R.layout.custom_witness_done,null);
                    lblWitnessName = (TextView) view.findViewById(R.id.lblWitnessName);
                    lblWitnessContact = (TextView) view.findViewById(R.id.lblWitnessContact);
                    lblWitnessAddress = (TextView) view.findViewById(R.id.lblWitnessAddress);

                    lblWitnessName.setText(txtWitnessName.getText().toString());
                    lblWitnessContact.setText(txtWitnessContact.getText().toString());
                    lblWitnessAddress.setText(txtWitnessAddress.getText().toString());

                    doneWitnessDetails.addView(view);

                }


            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


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
