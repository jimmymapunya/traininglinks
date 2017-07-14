package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

/**
 * Created by JimmyM on 2017/07/14.
 */

public class NotificationInfo {
    private String notificationDescription;
    private String notificationdate;

    public NotificationInfo() {
    }

    public NotificationInfo(String notificationDescription, String notificationdate) {
        this.notificationDescription = notificationDescription;
        this.notificationdate = notificationdate;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    public String getNotificationdate() {
        return notificationdate;
    }

    public void setNotificationdate(String notificationdate) {
        this.notificationdate = notificationdate;
    }
}
