package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

/**
 * Created by JimmyM on 2017/07/07.
 */

public class ComposeMessageActivity extends AppCompatActivity {

    private Context context = this;
    private Toolbar Toolbar;
    private Spinner spinner2;
    private Button btnSend;
    private EditText edtMessage;
    String URL = "http://innovationmessagehub.azurewebsites.net/api/MessageHub/CreateInboxMessage";
    private String body, subject, to;

    private TextView notificationCountIcon, inboxCountIcon;
    private FrameLayout notificationLayout, inboxLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);

        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        notificationCountIcon.setText(DashboardActivity.notificationCount);
        inboxCountIcon.setText(DashboardActivity.inboxCount);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Compose Message");

        //spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);


        setSupportActionBar(Toolbar);

        /*Back notificationicon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);

        //addItemsOnSpinner1();
        addItemsOnSpinner2();


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String itemSelected = parent.getItemAtPosition(position).toString();

                //if (!itemSelected.equals("To")) {
                    to = itemSelected;
                //}
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        edtMessage = (EditText) findViewById(R.id.edtMessage);
        btnSend = (Button)findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                body = edtMessage.getText().toString();

                if(body.equals(""))
                {
                    Toast.makeText(context,"Please enter message",Toast.LENGTH_LONG).show();
                }
                 if(to.equalsIgnoreCase("Select Tutor"))
                {
                    Toast.makeText(context,"Please select tutor",Toast.LENGTH_LONG).show();

                }
                else
                {
                    //createInboxMessage(URL, subject,body,to);
                    edtMessage.setText("");
                    Toast.makeText(context, "message sent" ,Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    void addItemsOnSpinner2()
    {

        List<String> list = new ArrayList<String>();
        list.add("Select Tutor");
        list.add("Jimmy Mapunya");
        list.add("Thabang Banks");
        list.add("Joe Smith");
        to = (String) spinner2.getSelectedItem();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);

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