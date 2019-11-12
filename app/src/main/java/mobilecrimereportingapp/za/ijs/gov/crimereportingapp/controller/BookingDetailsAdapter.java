package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.R;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.BookingDetails;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model.StatusDetails;
import mobilecrimereportingapp.za.ijs.gov.crimereportingapp.view.CaseDetailsActivity;

/**
 * Created by TsundzukaniM on 07-Aug-17.
 */

public class BookingDetailsAdapter extends ArrayAdapter<BookingDetails>{


    ArrayList<BookingDetails> bookingDetails;


    public BookingDetailsAdapter(Context context,  ArrayList<BookingDetails> bookingDetails) {
        super(context, R.layout.booking_details_list_row, bookingDetails);
        this.bookingDetails = bookingDetails;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View single_row = inflater.inflate(R.layout.booking_details_list_row, null,true);
        TextView tutorName = single_row.findViewById(R.id.txtTutorName);
        TextView txtSubject = single_row.findViewById(R.id.txtSubject);
        TextView status = single_row.findViewById(R.id.txtStatus);
        TextView txtDateTime = single_row.findViewById(R.id.txtDateTime);

        tutorName.setText(bookingDetails.get(position).getBookedTutor());
        txtSubject.setText(bookingDetails.get(position).getBookedSubject());
        status.setText(bookingDetails.get(position).getStatus());
        txtDateTime.setText(bookingDetails.get(position).getDateTime());

        return single_row;
    }

}