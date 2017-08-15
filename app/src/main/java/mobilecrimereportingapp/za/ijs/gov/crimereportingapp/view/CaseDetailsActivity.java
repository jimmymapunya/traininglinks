package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.CaseDetails;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.StatusDetails;

/**
 * Created by TsundzukaniM on 12-Jul-17.
 */



public class CaseDetailsActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar Toolbar;
    private Bundle bundle;
    private String caseNo;
    private String[] processNameArr;
    private String[] actionDateArr;
    private String[] actionLocationArr;
    private String currentProcess;

    private TextView notificationCountIcon, inboxCountIcon;
    private FrameLayout notificationLayout, inboxLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_details);

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);

        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        notificationCountIcon.setText(MainActivity.notificationCount);
        inboxCountIcon.setText(MainActivity.inboxCount);

         /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Case Details");
        bundle = getIntent().getExtras();
        String caseNo = bundle.getString("caseNo");

        TextView txtCaseNo = (TextView)findViewById(R.id.txtCaseNo);
        TextView txtVictim = (TextView)findViewById(R.id.txtVictim);
        TextView txtAccused = (TextView)findViewById(R.id.txtAccused);
        //TextView txtOffense = (TextView)findViewById(R.id.txtOffense);
        TextView txtCaseDesc = (TextView)findViewById(R.id.txtOffense);
        TextView txtInbox = (TextView)findViewById(R.id.txtInbox);
        TextView txtNotification = (TextView)findViewById(R.id.txtNotification);
        Button btnNavigateToCourt = (Button)findViewById(R.id.btnNavigateToCourt);

        txtCaseNo.setText("Case No "+caseNo);
        txtVictim.setText(bundle.getString("victim"));
        txtAccused.setText(bundle.getString("accused"));
        //txtOffense.setText(bundle.getString("offense"));
        txtCaseDesc.setText(bundle.getString("caseDesc"));
        txtInbox.setText(3+ "");
        txtNotification.setText(7+"");
        processNameArr = bundle.getStringArray("processNameArr");
        actionDateArr = bundle.getStringArray("actionDateArr");
        actionLocationArr = bundle.getStringArray("actionLocationArr");
        currentProcess = bundle.getString("currentProcess");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, processNameArr);

        Spinner spinProcessNames = (Spinner)findViewById(R.id.processNamesSpinner);
        spinProcessNames.setAdapter(adapter);
        btnNavigateToCourt.setVisibility(View.GONE);
        for(int x=0; x<processNameArr.length; x++){

            if(processNameArr[x].equals(currentProcess) && actionLocationArr[x]!=null){

                btnNavigateToCourt.setVisibility(View.VISIBLE);

            }
        }

        spinProcessNames.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        String processDesc ="";
                        if(processNameArr[position].equals("Investigate")){

                            if(processNameArr[position].equals(currentProcess)){
                                processDesc = "The case is currently being investigated from "+actionDateArr[position];

                            }else{
                                processDesc = "Investigation has been processed from "+actionDateArr[position]+" until "+actionDateArr[position+1];
                            }
                        }
                        if(processNameArr[position].equals("Arrest")){

                            processDesc = "Suspects has been arrested on the "+actionDateArr[position]+" to "+actionLocationArr[position];

                        }
                        if(processNameArr[position].equals("Bail Hearing")){

                            if(processNameArr[position].equals(currentProcess)){
                                processDesc = "Bail Hearing will take place on the  "+actionDateArr[position]+" at "+actionLocationArr[position]+ " 08:00 AM";

                            }else{
                                processDesc = "Bail hearing took place at "+actionLocationArr[position]+ " 08:00 AM on the "+actionDateArr[position];
                            }
                        }
                        if(processNameArr[position].equals("Trial")){

                            if(processNameArr[position].equals(currentProcess)){
                                processDesc = "This case is awaiting trial from  "+actionDateArr[position];

                            }else{
                                processDesc = "The trial period of the case started from "+actionDateArr[position]+" until "+actionDateArr[position+1];
                            }
                        }
                        if(processNameArr[position].equals("Verdict")){

                            if(processNameArr[position].equals(currentProcess)){
                                processDesc = "The court date for the verdict is  "+actionDateArr[position]+" at "+actionLocationArr[position]+ " 08:00 AM";

                            }else{
                                processDesc = "Verdict has taken place at "+actionLocationArr[position]+ " 08:00 AM on the "+actionDateArr[position];
                            }
                        }
                        if(processNameArr[position].equals("Aquit")){

                                processDesc = "This case has been dismissed on the  " + actionDateArr[position];
                        }
                        if(processNameArr[position].equals("Sentence")){

                            if(processNameArr[position].equals(currentProcess)){
                                processDesc = "Sentencing of this case will take place on the  "+actionDateArr[position]+" at "+actionLocationArr[position]+ " 08:00 AM";

                            }else{
                                processDesc = "The offender has been sentenced at "+actionLocationArr[position]+ " 08:00 AM on the "+actionDateArr[position];
                            }
                        }

                        TextView txtProcessDesc = (TextView)findViewById(R.id.txtProcessDesc);
                        txtProcessDesc.setText(processDesc);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {


                    }
                });

        setSupportActionBar(Toolbar);

        /*Back notificationicon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);

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

