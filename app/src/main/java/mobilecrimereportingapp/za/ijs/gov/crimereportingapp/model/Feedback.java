package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Created by ThatoM on 2017/07/17.
 */

public abstract class Feedback {
    private String starRating;
    private String additionalComments;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    private ArrayList<String> CheckBoxComments;
    private Button btnSubmitFeedback, btnNotNow;

    //Default constructor
    public Feedback(){

    }

    //Overloaded constructor
    public Feedback (String starRating, String additionalComments,CheckBox checkBox1, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4, Button btnSubmitFeedback, Button btnNotNow){
        setFeedbackValues(starRating, additionalComments, checkBox1, checkBox2, checkBox3, checkBox4, btnSubmitFeedback, btnNotNow);
    }

    //setters
    private void setFeedbackValues(String starRating, String additionalComments,CheckBox checkBox1, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4, Button btnSubmitFeedback, Button btnNotNow){
        setStarRating(starRating);
        setCheckBoxComments(checkBox1, checkBox2, checkBox3, checkBox4);
        setAdditionalComments(additionalComments);
        setFeedbackButtons(btnSubmitFeedback, btnNotNow);

    }

    public void setStarRating(String starRating){
        this.starRating = starRating;
    }


    public void setCheckBoxComments(CheckBox checkBox1, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4){
        this.checkBox1 = checkBox1;
        this.checkBox2 = checkBox2;
        this.checkBox3 = checkBox3;
        this.checkBox4 = checkBox4;
    }

    public void setAdditionalComments(String additionalComments){
        this.additionalComments = additionalComments;
    }


    public void setFeedbackButtons(Button btnSubmitFeedback, Button btnNotNow){
        this.btnSubmitFeedback = btnSubmitFeedback;
        this.btnNotNow = btnNotNow;
    }

    //getters
    public String getStarRating(){return starRating;}
    public ArrayList<String> getCheckBoxComments(){
        return CheckBoxComments;
    }
    public String getAdditionalComments(){
        return additionalComments;
    }

    // submits feedback when the submit feedback button is clicked else
    // does not submit when the Not Now button is clicked
    protected abstract void submitFeedback(Button btnSubmitFeedback, Button btnNotNow);

}
