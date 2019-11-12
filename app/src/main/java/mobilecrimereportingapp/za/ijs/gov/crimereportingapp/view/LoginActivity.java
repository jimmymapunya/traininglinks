package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

public class LoginActivity extends AppCompatActivity {

    private Context mContext;
    private Button btnSignIn;
    private EditText edtEmail,edtPassword;
    private int role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);

        /*Assign context to the declared context.*/
        mContext = this;

        initialize();

        btnSignIn.setOnClickListener(v -> {


            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            new Intent(mContext, LoginActivity.class);

            if(email.equalsIgnoreCase("jimmy@gmail.com") && password.equalsIgnoreCase("1234")){
                role = 0;
                Intent intent = new Intent(mContext, DashboardActivity.class);
                intent.putExtra("role", role);
                startActivity(intent);
            }
            else if(email.equalsIgnoreCase("vuyon@gmail.com") && password.equalsIgnoreCase("2468")) {
                role = 1;
                Intent intent = new Intent(mContext, DashboardActivity.class);
                intent.putExtra("role", role);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Incorrect Email/Password", Toast.LENGTH_LONG).show();
            }

        });
    }

    /*Initialize all the components.*/
    private void initialize()
    {
        btnSignIn = findViewById(R.id.btnSignIn);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

    }
}
