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
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_details);
         /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Case Details");
        bundle = getIntent().getExtras();
        String caseNo = bundle.getString("caseNo");

        TextView txtCaseNo = (TextView)findViewById(R.id.txtCaseNo);
        TextView txtVictim = (TextView)findViewById(R.id.txtVictim);
        TextView txtAccused = (TextView)findViewById(R.id.txtAccused);
        TextView txtOffense = (TextView)findViewById(R.id.txtOffense);
        TextView txtCaseDesc = (TextView)findViewById(R.id.txtCaseDesc);
        TextView txtInbox = (TextView)findViewById(R.id.txtInbox);
        TextView txtNotification = (TextView)findViewById(R.id.txtNotification);

        txtCaseNo.setText("Case No "+caseNo);
        txtVictim.setText(bundle.getString("victim"));
        txtAccused.setText(bundle.getString("accused"));
        txtOffense.setText(bundle.getString("offense"));
        txtCaseDesc.setText(bundle.getString("caseDesc"));
        txtInbox.setText(3+ "");
        txtNotification.setText(7+"");

        setSupportActionBar(Toolbar);

        /*Back icon for navigation drawer*/
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

