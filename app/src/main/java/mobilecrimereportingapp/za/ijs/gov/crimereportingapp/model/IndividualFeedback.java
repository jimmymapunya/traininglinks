package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by ThatoM on 2017/07/18.
 */

public class IndividualFeedback extends Feedback {
    private String caseNumber;
    private String actorRole;
    private String actorName;

    //default constructor
    public  IndividualFeedback(){

    }

    @Override
    protected void submitFeedback(Button btnSubmitFeedback, Button btnNotNow) {

    }

    //overloaded constructor
    public IndividualFeedback (String caseNumber, String actorRole, String actorName, String starRating, String additionalComments, CheckBox checkBox1, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4, Button btnSubmitFeedback, Button btnNotNow){

        super(starRating, additionalComments, checkBox1, checkBox2, checkBox3, checkBox4, btnSubmitFeedback, btnNotNow);
        this.caseNumber = caseNumber;
        this.actorRole = actorRole;
        this.actorName = actorName;

    }

    //setters
    public void setCaseNumber(String caseNumber){
        this.caseNumber = caseNumber;
    }

    public void setActorRole(String actorRole){
        this.actorRole = actorRole;
    }

    public void setActorName(String actorName){
        this.actorName = actorName;
    }

    //getters
    public  String getCaseNumber(){
        return  caseNumber;
    }

    public String getActorRole(){
        return actorRole;
    }

    public  String getActorName(){
        return  actorName;
    }
}
