package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.OverallFeedback;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.UserProfile;

/**
 * Created by ThatoM on 2017/07/12.
 * This is the class for overall feedback
 */

public class OverallFeedbackActivity extends AppCompatActivity {

    private Context context = this;
    private Toolbar Toolbar;

    private TextView notificationCountIcon, inboxCountIcon, happyQ;
    private FrameLayout notificationLayout, inboxLayout;

    private OverallFeedback overallFeedback;
    private EditText txtOverallFeedbackEditText;
    private RatingBar ratingBar;

    private String txtOverallFeedbackEditTextString;
    private String starRating;
    private String additionalComments;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6;
    private ArrayList<String> CheckBoxComments;
    private Button btnSubmit, btnNotNow;

    String device_id;
    String username;
    String role;
    String URL = "http://innovationmessagehub.azurewebsites.net/api/MessageHub/CreateFeedback";


    private  OverallFeedback oF = new OverallFeedback(starRating,  additionalComments, checkBox1,  checkBox2,  checkBox3,  checkBox4,  btnSubmit, btnNotNow);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_feedback);

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

        notificationLayout = (FrameLayout) findViewById(R.id.Notification);
        inboxLayout = (FrameLayout) findViewById(R.id.Inbox);

        notificationCountIcon = (TextView) findViewById(R.id.txtNotificationCount);
        inboxCountIcon = (TextView) findViewById(R.id.txtInboxCount);

        notificationCountIcon.setText(MainActivity.notificationCount);
        inboxCountIcon.setText(MainActivity.inboxCount);

        /*Toolbar and Buttons instantiation*/
        Toolbar = (Toolbar) findViewById(R.id.appBar);
        Toolbar.setTitle("Overall Feedback");

        setSupportActionBar(Toolbar);
        /*Back notificationicon for navigation drawer*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationDrawerFrag navigationDrawerFrag = (NavigationDrawerFrag)
                getSupportFragmentManager().findFragmentById(R.id.frag_nav_drawer);

        navigationDrawerFrag.setUpDrawer(R.id.frag_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), Toolbar);

        happyQ = (TextView) findViewById(R.id.happyQ);
        checkBox1 = (CheckBox) findViewById(R.id.checkBoxOF);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2OF);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox5OF);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4OF);
        checkBox5 = (CheckBox) findViewById(R.id.checkBox6OF);
        checkBox6 = (CheckBox) findViewById(R.id.checkBox7OF);

        //hide happyQ text view
        happyQ.setVisibility(View.INVISIBLE);
        //hide checkboxes on load
        checkBox1.setVisibility(View.INVISIBLE);
        checkBox2.setVisibility(View.INVISIBLE);
        checkBox3.setVisibility(View.INVISIBLE);
        checkBox4.setVisibility(View.INVISIBLE);
        checkBox5.setVisibility(View.INVISIBLE);
        checkBox6.setVisibility(View.INVISIBLE);

        //get additional comments
        txtOverallFeedbackEditText = (EditText) findViewById(R.id.txtOverallFeedbackEditText);

        btnSubmit = (Button) findViewById(R.id.btnSubmitOF);

        //get star rating
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        btnNotNow = (Button) findViewById(R.id.btnNotNowOF);

        //if rating value is changed,
        //display the current rating value in the on screen automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
           @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
               ratingBar = (RatingBar) findViewById(R.id.ratingBar);
               //show the star rating on change
               if( Double.valueOf(ratingBar.getRating()) >= 0.0 && Double.valueOf(ratingBar.getRating()) < 5) {
                   //hide happyQ text view
                   happyQ.setVisibility(View.VISIBLE);
                   checkBox1.setVisibility(View.VISIBLE);
                   checkBox2.setVisibility(View.VISIBLE);
                   checkBox3.setVisibility(View.VISIBLE);
                   checkBox4.setVisibility(View.VISIBLE);
                   checkBox5.setVisibility(View.VISIBLE);
                   checkBox6.setVisibility(View.VISIBLE);



               }else
                if((double) ratingBar.getRating() == 5){

                   //ratingBar = (RatingBar) findViewById(R.id.ratingBar);
                   happyQ.setText("What were you happy with?");
                   happyQ.setVisibility(View.VISIBLE);
                   checkBox1.setVisibility(View.VISIBLE);
                   checkBox2.setVisibility(View.VISIBLE);
                   checkBox3.setVisibility(View.VISIBLE);
                   checkBox4.setVisibility(View.VISIBLE);
                   checkBox5.setVisibility(View.VISIBLE);
                   checkBox6.setVisibility(View.VISIBLE);


               }else{
                   //hide them
                   //hide happyQ text view
                   happyQ.setVisibility(View.INVISIBLE);
                   checkBox1.setVisibility(View.INVISIBLE);
                   checkBox2.setVisibility(View.INVISIBLE);
                   checkBox3.setVisibility(View.INVISIBLE);
                   checkBox4.setVisibility(View.INVISIBLE);
                   checkBox5.setVisibility(View.INVISIBLE);
                   checkBox6.setVisibility(View.INVISIBLE);

               }
           }
        });
                starRating = String.valueOf(ratingBar.getRating());
                //get text in additional comments text box
                txtOverallFeedbackEditTextString = txtOverallFeedbackEditText.getText().toString();
                //Toast.makeText(context,starRating,Toast.LENGTH_LONG).show();

                    btnSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            checkBox1 = (CheckBox) findViewById(R.id.checkBoxOF);
                            checkBox2 = (CheckBox) findViewById(R.id.checkBox2OF);
                            checkBox3 = (CheckBox) findViewById(R.id.checkBox5OF);
                            checkBox4 = (CheckBox) findViewById(R.id.checkBox4OF);
                            checkBox5 = (CheckBox) findViewById(R.id.checkBox6OF);
                            checkBox6 = (CheckBox) findViewById(R.id.checkBox7OF);

                            //show the star rating on change
                            //starRating = String.valueOf(ratingBar.getRating());
                            if(String.valueOf(ratingBar.getRating()).equals("0.0")) {
                                //Toast.makeText(context,"Star Rating Test: "+ String.valueOf(ratingBar.getRating()),Toast.LENGTH_LONG).show();
                                //show pop up notifying user to star rate
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                // set title
                                alertDialogBuilder.setTitle("Please star rate");

                                //set dialog message
                                alertDialogBuilder.setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // if this button is clicked, just close
                                        // the dialog box and do nothing
                                        dialog.cancel();
                                    }
                                });
                                // create alert dialog
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                //show it
                                alertDialog.show();

                            }else{

                                //create and submit the overall feedback
                                createFeedback(URL, ratingBar, txtOverallFeedbackEditTextString, checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6);

                                //after feedback has been submitted show pop up
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                // set title
                                alertDialogBuilder.setTitle("Thank you for your feedback");

                                //set dialog message
                                alertDialogBuilder.setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //if this is clicked, close current activity and go back to provide feedback
                                        startActivity(new Intent(context, ProvideFeedback.class));
                                    }
                                });
                                // create alert dialog
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                //show it
                                alertDialog.show();

                            }

                        }
                    });

            //}
        //});

        //when the user does not want submit feedback
        btnNotNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ProvideFeedback.class));
            }
        });
    }

    //create feedback
    public void createFeedback(String url, final RatingBar ratingBar , final String additionalComments, final CheckBox checkBox1, final CheckBox checkBox2, final CheckBox checkBox3, final CheckBox checkBox4, final CheckBox checkBox5, final CheckBox checkBox6){

        //submit objects that are strings
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //test if values are received from interface
                        StringBuffer result = new StringBuffer();
                        result.append("checkbox1 : ").append(checkBox1.isChecked());
                        result.append("\ncheckbox2 : ").append(checkBox2.isChecked());
                        result.append("\ncheckbox3 : ").append(checkBox3.isChecked());
                        result.append("\ncheckbox4 : ").append(checkBox4.isChecked());

                        /**Toast.makeText(context, result.toString(),Toast.LENGTH_LONG).show();
                        Toast.makeText(context,starRating,Toast.LENGTH_LONG).show();
                        //Tell user values have been submitted
                        Toast.makeText(context,"Sent",Toast.LENGTH_LONG).show();*/


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error !=null && error.toString() !=null){
                            Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }else{
                            Toast.makeText(context,"Something went wrong",Toast.LENGTH_LONG).show();
                        }

                    }
                }) {

            @Override
            protected Map<String, String> getParams() {

                //Values to post
                username = UserProfile.Username;
                role = "admin";
                device_id = "1";

                //if rating value is changed,
                //display the current rating value in the on screen automatically
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        //show the star rating on change
                        starRating = String.valueOf(ratingBar.getRating());
                        //Toast.makeText(context,starRating,Toast.LENGTH_LONG).show();
                    }
                });
                //get value of star ratings
                starRating = String.valueOf(ratingBar.getRating());
                //get values of checkboxes


                Map<String, String> params = new HashMap<String, String>();
                params.put("AuthDetail.UserName", username);
                params.put("AuthDetail.Role", role);
                params.put("AuthDetail.DeviceId", device_id);
                params.put("StarRating", starRating);
                params.put("AdditionalComments",additionalComments);

                //submit object that are not strings
                JSONObject object = new JSONObject();
                try {
                    object.put("checkBox1",checkBox1.isChecked());
                    object.put("checkBox2",checkBox2.isChecked());
                    object.put("checkBox3",checkBox3.isChecked());
                    object.put("checkBox4",checkBox4.isChecked());
                    //object.put("checkBox5",checkBox5.isChecked());
                    //object.put("checkBox6",checkBox5.isChecked());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
