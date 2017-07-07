package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

/**
 * Created by TsundzukaniM on 2017/07/06.
 */

import java.util.ArrayList;

/**
 * Created by TsundzukaniM on 2017/07/05.
 */

public class CaseDetails {

    private String caseNo;
    private String victim;
    private boolean accused;
    private String offense;
    private String caseDesc;
    private ArrayList<StatusDetails> status;

    public CaseDetails(){

        status = new ArrayList<StatusDetails>();

    }
    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getVictim() {
        return victim;
    }

    public void setVictim(String victim) {
        this.victim = victim;
    }

    public boolean isAccused() {
        return accused;
    }

    public void setAccused(boolean accused) {
        this.accused = accused;
    }

    public String getOffense() {
        return offense;
    }

    public void setOffense(String offense) {
        this.offense = offense;
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    public ArrayList<StatusDetails> getStatus() {
        return status;
    }

    public void setStatus(StatusDetails statusDetails) {
        status.add(statusDetails);
    }

}