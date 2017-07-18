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
    private String accused;
    private String beforeCrime;
    private String duringCrime;
    private String injuries;
    private String crimeSurroundings;
    private String afterCrime;
    private String offenderName;
    private String offenderContact;
    private String offenderAddress;
    private String descriptionTattoos;
    private String appearance;
    private String WitnessName;
    private String WitnessContact;
    private String WitnessAddress;
    private String offense;
    private String caseDesc;
    private ArrayList<StatusDetails> status;


    public CaseDetails(){

        status = new ArrayList<StatusDetails>();

    }

    public CaseDetails(String caseNo, String victim, String accused, String offense, String caseDesc, ArrayList<StatusDetails> status) {
        this.caseNo = caseNo;
        this.victim = victim;
        this.accused = accused;
        this.offense = offense;
        this.caseDesc = caseDesc;
        this.status = status;
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

    public String getAccused() {
        return accused;
    }

    public void setAccused(String accused) {
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

    public void setStatus(ArrayList<StatusDetails> status) {

       this.status = status;

    }

    public String getBeforeCrime() {
        return beforeCrime;
    }

    public void setBeforeCrime(String beforeCrime) {
        this.beforeCrime = beforeCrime;
    }

    public String getDuringCrime() {
        return duringCrime;
    }

    public void setDuringCrime(String duringCrime) {
        this.duringCrime = duringCrime;
    }

    public String getAfterCrime() {
        return afterCrime;
    }

    public void setAfterCrime(String afterCrime) {
        this.afterCrime = afterCrime;
    }

    public String getInjuries() {
        return injuries;
    }

    public void setInjuries(String injuries) {
        this.injuries = injuries;
    }

    public String getCrimeSurroundings() {
        return crimeSurroundings;
    }

    public void setCrimeSurroundings(String crimeSurroundings) {
        this.crimeSurroundings = crimeSurroundings;
    }

    public String getOffenderName() {
        return offenderName;
    }

    public void setOffenderName(String offenderName) {
        this.offenderName = offenderName;
    }

    public String getOffenderContact() {
        return offenderContact;
    }

    public void setOffenderContact(String offenderContact) {
        this.offenderContact = offenderContact;
    }

    public String getOffenderAddress() {
        return offenderAddress;
    }

    public void setOffenderAddress(String offenderAddress) {
        this.offenderAddress = offenderAddress;
    }

    public String getDescriptionTattoos() {
        return descriptionTattoos;
    }

    public void setDescriptionTattoos(String descriptionTattoos) {
        this.descriptionTattoos = descriptionTattoos;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getWitnessName() {
        return WitnessName;
    }

    public void setWitnessName(String witnessName) {
        WitnessName = witnessName;
    }

    public String getWitnessContact() {
        return WitnessContact;
    }

    public void setWitnessContact(String witnessContact) {
        WitnessContact = witnessContact;
    }

    public String getWitnessAddress() {
        return WitnessAddress;
    }

    public void setWitnessAddress(String witnessAddress) {
        WitnessAddress = witnessAddress;
    }
}