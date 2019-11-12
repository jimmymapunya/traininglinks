package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

public class BookingDetails {

    private String bookedTutor;
    private String bookedSubject;
    private String status;
    private String dateTime;

    public BookingDetails(String bookedTutor, String bookedSubject, String status, String dateTime) {
        this.bookedTutor = bookedTutor;
        this.bookedSubject = bookedSubject;
        this.status = status;
        this.dateTime = dateTime;
    }

    public String getBookedTutor() {
        return bookedTutor;
    }

    public void setBookedTutor(String bookedTutor) {
        this.bookedTutor = bookedTutor;
    }

    public String getBookedSubject() {
        return bookedSubject;
    }

    public void setBookedSubject(String bookedSubject) {
        this.bookedSubject = bookedSubject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}