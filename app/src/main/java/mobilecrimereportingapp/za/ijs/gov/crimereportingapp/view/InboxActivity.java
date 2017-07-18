package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller.InboxAdapter;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.InboxModel;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.NotificationInfo;

public class InboxActivity extends AppCompatActivity {

    private Toolbar Toolbar;
    Context context = this;
    private ListView lv;

    ArrayList<InboxModel> inbox;
    InboxAdapter adapter;

    private TextView notificationCountIcon, inboxCountIcon;
    private FrameLayout notificationLayout;
     JSONArray jsonArrayMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Inbox");

        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        notificationCountIcon.setText(MainActivity.notificationCount);
        inboxCountIcon.setText(MainActivity.inboxCount);

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NotificationActivity.class));
            }
        });

        lv = (ListView) findViewById(R.id.listview);
        inbox = new ArrayList<>();

        for(int x=0;x<MainActivity.jsonArrayInboxes.length();x++)
        {

            try {
                JSONObject jsonObjectInbox = MainActivity.jsonArrayInboxes.getJSONObject(x);

                String subject = jsonObjectInbox.getString("subject");
                String inboxDate = jsonObjectInbox.getString("inboxDate");
                jsonArrayMessages = jsonObjectInbox.getJSONArray("messages");

                String from ="";

                for(int i=0;i<jsonArrayMessages.length();i++)
                {
                    JSONObject jsonObjectMessages = jsonArrayMessages.getJSONObject(i);
                     //body = jsonObjectMessages.getString("body");
                     from = jsonObjectMessages.getString("from");

                }
                String date = reFormatDate(inboxDate);
                inbox.add(new InboxModel(subject,date, from,jsonArrayMessages));

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        adapter = new InboxAdapter(context,inbox);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(context,InboxMessagesActivity.class);
                intent.putExtra("JsonArrayMessages", inbox.get(position).getJsonArray().toString());
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
    private String reFormatDate(String dateIn) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = simpleDateFormat.parse(dateIn);
        simpleDateFormat = new SimpleDateFormat("dd-MMM HH:mm");
        return simpleDateFormat.format(date);
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
