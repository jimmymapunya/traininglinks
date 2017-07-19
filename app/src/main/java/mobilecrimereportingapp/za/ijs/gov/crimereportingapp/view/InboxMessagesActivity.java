package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

public class InboxMessagesActivity extends AppCompatActivity {

    private Toolbar Toolbar;
    Context context = this;

    private TextView notificationCountIcon, inboxCountIcon;
    private FrameLayout notificationLayout,inboxLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_messages);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Messages");

        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        notificationCountIcon.setText(MainActivity.notificationCount);
        inboxCountIcon.setText(MainActivity.inboxCount);

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);
        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NotificationActivity.class));
            }
        });
        inboxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, InboxActivity.class));
            }
        });


        LinearLayout layout = (LinearLayout)findViewById(R.id.lay);
        LayoutInflater inflater = LayoutInflater.from(context);

        Bundle extras = getIntent().getExtras();
        String tmp = extras.getString("JsonArrayMessages");

        //Toast.makeText(context, ""+tmp, Toast.LENGTH_LONG).show();

        try {
            JSONArray jsonArray = new JSONArray(tmp);
             for(int x=0;x<jsonArray.length();x++)
            {
                final View view = inflater.inflate(R.layout.message_layout, null);
                final TextView txtBody = (TextView) view.findViewById(R.id.txtMessage);
                final TextView txtDate = (TextView) view.findViewById(R.id.txtDate);
                JSONObject jsonMessage = jsonArray.getJSONObject(x);
                String body = jsonMessage.getString("body");
                String messageDate = jsonMessage.getString("messageDate");
                String finalDate = reFormatDate(messageDate);
                txtBody.setText(body);
                txtDate.setText(finalDate);
                layout.addView(view);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


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
}
