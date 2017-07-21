package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by ThatoM on 2017/07/18.
 */

public class EventFeedback extends Feedback {

    private String caseNumber;
    private String phase;

    //default constructor
    public EventFeedback(){

    }

    @Override
    protected void submitFeedback(Button btnSubmitFeedback, Button btnNotNow) {

    }

    //overloaded constructor
    public EventFeedback (String caseNumber, String phase, String starRating, String additionalComments, CheckBox checkBox1, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4, Button btnSubmitFeedback, Button btnNotNow){

        super(starRating, additionalComments, checkBox1, checkBox2, checkBox3, checkBox4, btnSubmitFeedback, btnNotNow);
        this.caseNumber = caseNumber;
        this.phase = phase;

    }

    //setters
    public void setCaseNumber(String caseNumber){
        this.caseNumber = caseNumber;
    }
    public void setPhase(String phase){
        this.phase = phase;
    }

    //getters
    public String getCaseNumber(){
        return caseNumber;
    }
    public String getPhase(){
        return phase;
    }

}
