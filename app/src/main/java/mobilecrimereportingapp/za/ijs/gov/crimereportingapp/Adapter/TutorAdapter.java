package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.TutorModel;


public class TutorAdapter extends ArrayAdapter<TutorModel> {

    ArrayList<TutorModel> tutor;

    public TutorAdapter(Context context, ArrayList<TutorModel> tutor) {
        super(context, R.layout.tutor_items, tutor);
        this.tutor = tutor;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.tutor_items, null,true);
        TextView tutorFullName = single_row.findViewById(R.id.tutorFullName);
        tutorFullName.setText(tutor.get(position).getFullName());

        return single_row;
    }
}