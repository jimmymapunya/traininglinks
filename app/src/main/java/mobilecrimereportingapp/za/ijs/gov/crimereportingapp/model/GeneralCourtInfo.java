package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

/**
 * Created by MaribolleR on 2017/04/17.
 */

public class GeneralCourtInfo {
    /*Declaration of data members for general court info*/

    private int imageID;
    private String courtName;

    public GeneralCourtInfo(){

    }

    public GeneralCourtInfo(String courtName) {

        this.courtName = courtName;
    }

    /*Property methods for data members*/
    public int getImageID() {

        return imageID;
    }

    public void setImageID(int imageID) {

        this.imageID = imageID;
    }

    public String getCourtName() {

        return courtName;
    }

    public void setCourtName(String courtName) {

        this.courtName = courtName;
    }
}
