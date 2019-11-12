package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
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


import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller.NotificationAdapter;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.NotificationInfo;

public class NotificationActivity extends AppCompatActivity {
    private Toolbar Toolbar;
    Context context = this;
    private ListView lv;

    ArrayList<NotificationInfo> notifications;
    NotificationAdapter adapter;

    private TextView notificationCountIcon, inboxCountIcon;
    private FrameLayout inboxLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Notification");

        lv = (ListView) findViewById(R.id.listview);

        notifications = new ArrayList<>();

        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);
        inboxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, InboxActivity.class));
            }
        });


        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        notificationCountIcon.setText(DashboardActivity.notificationCount);
        inboxCountIcon.setText(DashboardActivity.inboxCount);


        for(int x = 0; x< DashboardActivity.jsonArrayNotification.length(); x++)
        {

            try {
                JSONObject jsonObjectNotification = DashboardActivity.jsonArrayNotification.getJSONObject(x);
                String notificationDescription = jsonObjectNotification.getString("description");
                String notificationDate = jsonObjectNotification.getString("notificationDate");
                //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
               String date = reFormatDate(notificationDate);
                String finalDescription = "";

                /**if(notificationDescription.length()<=20)
                {
                    finalDescription = notificationDescription + "...";
                }
                else
                {
                    finalDescription = notificationDescription.substring(19) + "...";
                }**/

                notifications.add(new NotificationInfo(notificationDescription, date));

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

        adapter = new NotificationAdapter(context,notifications);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(context)
                        .setTitle("Notification")
                        .setMessage(notifications.get(position).getNotificationDescription())
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
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
