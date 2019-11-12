package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;

public class RegisterActivity extends AppCompatActivity {

    private Context mContext;
    private EditText edtFirstName,edtSurname, edtEmail, edtPhoneNumber, edtGrade, edtPassword, edtArea;
    private String firstname, surname, email, phoneNumber, grade, password, area;
    private Button btnTutor, btnStudent;
    private Spinner spinnerProvince, spinnerTutoringMethod, spinnerSubjects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Register");
        setSupportActionBar(toolbar);

        /*Assign context to the declared context.*/
        mContext = this;
        initialize();

        addProvinceOnSpinner();
        addTutoringMethodOnSpinner();
        //addSubjectsOnSpinner();
//        spinnerProvince.setOnItemClickListener((parent, view, position, id) -> {
//
//
//        });

        btnTutor.setOnClickListener(v -> {

            firstname = edtFirstName.getText().toString();
            surname = edtSurname.getText().toString();
            email = edtEmail.getText().toString();
            phoneNumber = edtPhoneNumber.getText().toString();
            grade = edtGrade.getText().toString();
            password = edtPassword.getText().toString();
            area = edtArea.getText().toString();

            //new Intent(mContext, RegisterTutorActivity.class);
            Intent intent = new Intent(mContext, RegisterTutorActivity.class);
            intent.putExtra("firstname", firstname);
            intent.putExtra("surname", surname);
            intent.putExtra("email", email);
            intent.putExtra("phoneNumber", phoneNumber);

            intent.putExtra("province", "Western Cape");
            intent.putExtra("area", area);
            startActivity(intent);

        });

        btnStudent.setOnClickListener(v -> {

            Intent intent = new Intent(mContext, RegisterStudentActivity.class);
            startActivity(intent);

        });
    }
    /*Initialize all the components.*/
    private void initialize()
    {
        //Edittexts
        edtFirstName = findViewById(R.id.edtFirstName);
        edtSurname = findViewById(R.id.edtSurname);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtGrade = findViewById(R.id.edtGrade);
        edtPassword = findViewById(R.id.edtPassword);
        edtArea = findViewById(R.id.edtArea);

        //Buttons
        btnTutor = findViewById(R.id.btnTutor);
        btnStudent = findViewById(R.id.btnStudent);

        //Spinner
        spinnerProvince = findViewById(R.id.spinnerProvince);
        spinnerTutoringMethod = findViewById(R.id.spinnerTutoringMethod);
        //spinnerSubjects.findViewById(R.id.spinnerSubjects);

    }

    void addProvinceOnSpinner(){
        List<String> province = new ArrayList<>();
        province.add("Gauteng");
        province.add("Limpopo");
        province.add("Northern Cape");
        province.add("Western Cape");
        province.add("North West");
        province.add("Eastern cape");
        province.add("Mpumalanga");
        province.add("Kwazulu Natal");
        province.add("Free State");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_spinner_item, province);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(dataAdapter);
    }
    void addTutoringMethodOnSpinner(){
        List<String> tutoringMethod = new ArrayList<>();
        tutoringMethod.add("Face to Face");
        tutoringMethod.add("Video Call");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_spinner_item, tutoringMethod);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTutoringMethod.setAdapter(dataAdapter);
    }
    void addSubjectsOnSpinner(){
        List<String> subjects = new ArrayList<>();
        subjects.add("Maths");
        subjects.add("Biology");
        subjects.add("English");
        subjects.add("Physical Science");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_spinner_item, subjects);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubjects.setAdapter(dataAdapter);
    }




}
