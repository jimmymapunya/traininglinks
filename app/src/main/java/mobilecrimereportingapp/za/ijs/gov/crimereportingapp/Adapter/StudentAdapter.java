package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.ArrayList;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.StudentModel;

public class StudentAdapter extends ArrayAdapter<StudentModel> {

    ArrayList<StudentModel> student;

    public StudentAdapter(Context context, ArrayList<StudentModel> studentObj) {
        super(context, R.layout.tutor_items, studentObj);
        this.student = studentObj;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.tutor_items, null,true);
        TextView studentFullName = single_row.findViewById(R.id.tutorFullName);
        studentFullName.setText(student.get(position).getFullName());

        return single_row;
    }
}
