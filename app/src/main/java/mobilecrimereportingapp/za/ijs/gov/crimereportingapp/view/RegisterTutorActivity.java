package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

public class RegisterTutorActivity extends AppCompatActivity {

    private Context mContext;
    private Button btnCompleteTutor, btnUpload;
    private String firstname, surname, email, phoneNumber, grade, province, area, password;
    private String appURL = "https://apitutormari.azurewebsites.net/tutor/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tutor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Continue");
        setSupportActionBar(toolbar);

        /*Assign context to the declared context.*/
        mContext = this;
        initialize();

        btnCompleteTutor.setOnClickListener(v -> {

                Toast.makeText(getApplicationContext(), "Registration Complete " , Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, DashboardActivity.class);
                startActivity(intent);



        });

        btnUpload.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("text/plain");
            intent.setData(Uri.parse("mailto:IT@RMAsoft.NET"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Email from My app");
            intent.putExtra(Intent.EXTRA_TEXT, "Place your email message here ...");
            startActivity(Intent.createChooser(intent, "Send Email"));

        });

    }

    /*Initialize all the components.*/
    private void initialize() {

        firstname = getIntent().getStringExtra("firstname");
        surname = getIntent().getStringExtra("surname");
        email = getIntent().getStringExtra("email");
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        grade = getIntent().getStringExtra("grade");
        province = getIntent().getStringExtra("province");
        area = getIntent().getStringExtra("area");

        //Buttons
        btnCompleteTutor = findViewById(R.id.btnCompleteTutor);
        btnUpload = findViewById(R.id.btnUpload);

    }




}
