package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by ThatoM on 2017/07/18.
 */

public class OverallFeedback extends Feedback {

    //default constructor
    public  OverallFeedback(){

    }

    //overloaded constructor
    public OverallFeedback (String starRating, String additionalComments, CheckBox checkBox1, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4, Button btnSubmitFeedback, Button btnNotNow){

        super(starRating, additionalComments, checkBox1, checkBox2, checkBox3, checkBox4, btnSubmitFeedback, btnNotNow);

    }

    @Override
    protected void submitFeedback(Button btnSubmitFeedback, Button btnNotNow) {

    }

}
