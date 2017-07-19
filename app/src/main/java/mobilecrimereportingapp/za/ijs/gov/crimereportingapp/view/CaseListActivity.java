package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller.CaseAdapter;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.CaseDetails;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.StatusDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CaseListActivity extends AppCompatActivity {

    private Toolbar Toolbar;
    private Context context = this;
    ArrayList<CaseDetails> listdao = new ArrayList<>();

    private TextView notificationCountIcon, inboxCountIcon;
    private FrameLayout notificationLayout, inboxLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_list);

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);

        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        notificationCountIcon.setText(MainActivity.notificationCount);
        inboxCountIcon.setText(MainActivity.inboxCount);

         /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("My Cases");

        Button btnHeading=(Button)findViewById(R.id.btnHeading);

        ImageView sortImage=(ImageView)findViewById(R.id.img_sort);
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutExpand);

        sortImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relativeLayout.setVisibility(View.VISIBLE);
            }
        });
        btnHeading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relativeLayout.setVisibility(View.INVISIBLE);
            }
        });

        getIntent().removeExtra("key");
        getIntent().removeExtra("caseNo");
        getIntent().removeExtra("victim");
        getIntent().removeExtra("accused");
        getIntent().removeExtra("offense");
        getIntent().removeExtra("caseDesc");
        getIntent().removeExtra("processNameArr");
        getIntent().removeExtra("actionDateArr");
        getIntent().removeExtra("actionLocationArr");
        getIntent().removeExtra("currentProcess");

        ArrayList<StatusDetails> statusDetailsList1 = new ArrayList<>();
        ArrayList<StatusDetails> statusDetailsList2 = new ArrayList<>();
        ArrayList<StatusDetails> statusDetailsList3 = new ArrayList<>();
        ArrayList<StatusDetails> statusDetailsList4 = new ArrayList<>();
        ArrayList<StatusDetails> statusDetailsList5 = new ArrayList<>();


        statusDetailsList1.add(new StatusDetails("02/2016/98", "Investigate", false, "22-03-2017", null, "01-05-2017"));
        statusDetailsList1.add(new StatusDetails("02/2016/98", "Arrest", false, "22-03-2017", "Sunnyside Police Station", "01-05-2017"));
        statusDetailsList1.add(new StatusDetails("02/2016/98", "Bail Hearing", true, "21-08-2017", "Johannesburg Magistrate Court", "01-05-2017"));

        statusDetailsList2.add(new StatusDetails("03/2015/99", "Investigate", false, "22-03-2017", null, "01-05-2017"));
        statusDetailsList2.add(new StatusDetails("03/2015/99", "Arrest", false, "22-03-2017", "Sunnyside Police Station", "01-05-2017"));
        statusDetailsList2.add(new StatusDetails("03/2015/99", "Bail Hearing", false, "21-08-2017", "Johannesburg Magistrate Court", "01-05-2017"));
        statusDetailsList2.add(new StatusDetails("03/2015/99", "Trial", false, "22-03-2017", null, "01-05-2017"));
        statusDetailsList2.add(new StatusDetails("03/2015/99", "Verdict", false, "22-03-2017", "Sunnyside Police Station", "01-05-2017"));
        statusDetailsList2.add(new StatusDetails("03/2015/99", "Sentence", true, "21-08-2017", "Johannesburg Magistrate Court", "01-05-2017"));

        statusDetailsList3.add(new StatusDetails("04/2017/44", "Investigate", false, "22-03-2017", null, "01-05-2017"));
        statusDetailsList3.add(new StatusDetails("04/2017/44", "Arrest", false, "22-03-2017", "Sunnyside Police Station", "01-05-2017"));
        statusDetailsList3.add(new StatusDetails("04/2017/44", "Bail Hearing", false, "21-08-2017", "Johannesburg Magistrate Court", "01-05-2017"));
        statusDetailsList3.add(new StatusDetails("04/2017/44", "Trial", true, "22-03-2017", null, "01-05-2017"));

        statusDetailsList4.add(new StatusDetails("06/2014/11", "Investigate", false, "22-03-2017", null, "01-05-2017"));
        statusDetailsList4.add(new StatusDetails("06/2014/11", "Aquit", false, "22-03-2017", "Sunnyside Police Station", "01-05-2017"));

        statusDetailsList5.add(new StatusDetails("06/2017/25", "Investigate", false, "22-03-2017", null, "01-05-2017"));
        statusDetailsList5.add(new StatusDetails("06/2017/25", "Arrest", false, "22-03-2017", "Sunnyside Police Station", "01-05-2017"));
        statusDetailsList5.add(new StatusDetails("06/2017/25", "Bail Hearing", false, "21-08-2017", "Johannesburg Magistrate Court", "01-05-2017"));
        statusDetailsList5.add(new StatusDetails("06/2017/25", "Trial", true, "22-03-2017", null, "01-05-2017"));

        listdao.add(new CaseDetails("02/2016/98", "Mazit Mikel", "Lindiwe Maponya", "Attempted Murder", "The accused is suspected of sturbing a girl with a knife", statusDetailsList1));
        listdao.add(new CaseDetails("03/2015/99", "John Dumelo", "Hlengiwe Mkhabele", "Attempted Murder", "The accused is suspected of sturbing a girl with a knife", statusDetailsList2));
        listdao.add(new CaseDetails("04/2017/44", "Yvone Nelson", "Adam Rabopape", "Attempted Murder", "The accused is suspected of sturbing a girl with a knife", statusDetailsList3));
        listdao.add(new CaseDetails("06/2014/11", "Nadia Beure", "Khatisa Chabalala", "Attempted Murder", "The accused is suspected of sturbing a girl with a knife", statusDetailsList4));
        listdao.add(new CaseDetails("06/2017/25", "Kackey Aphiah", "Siduel Maxakeni", "Attempted Murder", "The accused is suspected of sturbing a girl with a knife", statusDetailsList5));


        // Each row in the list stores country name, currency and flag
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < listdao.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();

            int status_last_index = (listdao.get(i).getStatus().size()) - 1;
            hm.put("caseNo", listdao.get(i).getCaseNo());
            hm.put("caseContent", "Crime: " + listdao.get(i).getOffense() + "Suspect(s) " + listdao.get(i).getAccused());
            hm.put("dateCreated", listdao.get(i).getStatus().get(status_last_index).getDateCreated());
            String status = "";
            for(int y=0; y< listdao.get(i).getStatus().size(); y++){


                if(listdao.get(i).getStatus().get(y).isIsCurrent()){
                    status = status  + listdao.get(i).getStatus().get(y).getProcessName() + "*#";
                }else{
                    status = status  + listdao.get(i).getStatus().get(y).getProcessName() + "#";
                }

            }

            hm.put("status"+ (i), status);
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = {"caseNo", "caseContent", "dateCreated"};

        // Ids of views in listview_layout
        int[] to = {R.id.txtCaseNo, R.id.txtCaseContent, R.id.txtDateCreated};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item

        CaseAdapter adapter = new CaseAdapter(getBaseContext(), aList, R.layout.activity_case_list_row, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = (ListView) findViewById(R.id.listview);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                getIntent().removeExtra("key");
                getIntent().removeExtra("caseNo");
                getIntent().removeExtra("victim");
                getIntent().removeExtra("accused");
                getIntent().removeExtra("offense");
                getIntent().removeExtra("caseDesc");
                getIntent().removeExtra("processNameArr");
                getIntent().removeExtra("actionDateArr");
                getIntent().removeExtra("actionLocationArr");
                getIntent().removeExtra("currentProcess");

                ArrayList<StatusDetails> statusDetailsList = listdao.get(position).getStatus();
                Intent intent =new Intent(context, CaseDetailsActivity.class);

                String[] processName = new String[statusDetailsList.size()];
                String[] actionDate = new String[statusDetailsList.size()];
                String[] actionLocation = new String[statusDetailsList.size()];
                boolean[] isCurrent = new boolean[statusDetailsList.size()];
                String currentProcess ="";
                for(int x=0; x<statusDetailsList.size(); x++){

                    processName[x] = statusDetailsList.get(x).getProcessName();
                    actionDate[x] = statusDetailsList.get(x).getActionDate();
                    actionLocation[x] = statusDetailsList.get(x).getActionLocation();
                    if(statusDetailsList.get(x).isIsCurrent()){
                        currentProcess =  statusDetailsList.get(x).getProcessName();
                    }
                }

                intent.putExtra("caseNo",listdao.get(position).getCaseNo());
                intent.putExtra("victim",listdao.get(position).getVictim());
                intent.putExtra("accused",listdao.get(position).getAccused());
                intent.putExtra("offense",listdao.get(position).getOffense());
                intent.putExtra("caseDesc",listdao.get(position).getCaseDesc());
                intent.putExtra("processNameArr",processName);
                intent.putExtra("actionDateArr",actionDate);
                intent.putExtra("actionLocationArr",actionLocation);
                intent.putExtra("currentProcess",currentProcess);
                startActivity(intent);
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
    public ArrayList<CaseDetails> getCaseDetails(){
        return listdao;
    }

}

