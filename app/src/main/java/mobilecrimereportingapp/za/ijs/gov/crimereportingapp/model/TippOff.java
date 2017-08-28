package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

/**
 * Created by TsundzukaniM on 24-Aug-17.
 */

public class TippOff {

    private String offenderNames, offenderAddress, offenderCell, offenceComitted, dateOfOffence, locationOffence, offenceDetail, victimNames, victimAddress, victimCell, caseNo;
    private boolean previous;

    public String getOffenderNames() {
        return offenderNames;
    }

    public void setOffenderNames(String offenderNames) {
        this.offenderNames = offenderNames;
    }

    public String getOffenderAddress() {
        return offenderAddress;
    }

    public void setOffenderAddress(String offenderAddress) {
        this.offenderAddress = offenderAddress;
    }

    public String getOffenderCell() {
        return offenderCell;
    }

    public void setOffenderCell(String offenderCell) {
        this.offenderCell = offenderCell;
    }

    public String getOffenceComitted() {
        return offenceComitted;
    }

    public void setOffenceComitted(String offenceComitted) {
        this.offenceComitted = offenceComitted;
    }

    public String getDateOfOffence() {
        return dateOfOffence;
    }

    public void setDateOfOffence(String dateOfOffence) {
        this.dateOfOffence = dateOfOffence;
    }

    public String getLocationOffence() {
        return locationOffence;
    }

    public void setLocationOffence(String locationOffence) {
        this.locationOffence = locationOffence;
    }

    public String getOffenceDetail() {
        return offenceDetail;
    }

    public void setOffenceDetail(String offenceDetail) {
        this.offenceDetail = offenceDetail;
    }

    public String getVictimNames() {
        return victimNames;
    }

    public void setVictimNames(String victimNames) {
        this.victimNames = victimNames;
    }

    public String getVictimAddress() {
        return victimAddress;
    }

    public void setVictimAddress(String victimAddress) {
        this.victimAddress = victimAddress;
    }

    public String getVictimCell() {
        return victimCell;
    }

    public void setVictimCell(String victimCell) {
        this.victimCell = victimCell;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public boolean isPrevious() {
        return previous;
    }

    public void setPrevious(boolean previous) {
        this.previous = previous;
    }
}
