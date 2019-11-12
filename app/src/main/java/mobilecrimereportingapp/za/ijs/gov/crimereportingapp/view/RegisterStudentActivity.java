package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

public class RegisterStudentActivity extends AppCompatActivity {

    private Context mContext;
    Button btnCompleteStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Continue");
        setSupportActionBar(toolbar);

        /*Assign context to the declared context.*/
        mContext = this;
        initialize();

        btnCompleteStudent.setOnClickListener(v -> {

            Toast.makeText(getApplicationContext(), "Registration Complete ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mContext, DashboardActivity.class);
            startActivity(intent);
        });

    }

    /*Initialize all the components.*/
    private void initialize() {

        btnCompleteStudent = findViewById(R.id.btnCompleteStudent);
    }
}

