package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller.NotificationAdapter;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.NotificationInfo;

public class NotificationActivity extends AppCompatActivity {
    private Toolbar Toolbar;
    Context context = this;
    private ListView lv;

    ArrayList<NotificationInfo> notifications;
    NotificationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Notification");

        lv = (ListView) findViewById(R.id.listview);

        notifications = new ArrayList<>();

        for(int x=0;x<MainActivity.jsonArrayNotification.length();x++)
        {

            try {
                JSONObject jsonObjectNotification = MainActivity.jsonArrayNotification.getJSONObject(x);
                String notificationDescription = jsonObjectNotification.getString("description");
                String notificationDate = jsonObjectNotification.getString("notificationDate");
                //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
               // String date = format.format(Date.parse(notificationDate));


                notifications.add(new NotificationInfo(notificationDescription, notificationDate));

            } catch (JSONException e) {
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
}