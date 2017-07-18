package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

/**
 * Created by JimmyM on 2017/07/17.
 */

public class InboxModel {

    private String subject;
    private String inboxDate;
    private String from;
    private String message;

    public InboxModel() {
    }

    public InboxModel(String subject, String inboxDate, String from) {
        this.subject = subject;
        this.inboxDate = inboxDate;
        this.from = from;
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getInboxDate() {
        return inboxDate;
    }

    public void setInboxDate(String inboxDate) {
        this.inboxDate = inboxDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
