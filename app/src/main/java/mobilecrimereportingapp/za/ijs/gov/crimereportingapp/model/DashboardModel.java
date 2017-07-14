package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

/**
 * Created by JimmyM on 2017/07/13.
 */

public class DashboardModel {

    String notificationCount, inboxCount, myCaseCount;


    public DashboardModel() {


    }
    public DashboardModel(String notificationCount, String inboxCount, String myCaseCount) {
        this.notificationCount = notificationCount;
        this.inboxCount = inboxCount;
        this.myCaseCount = myCaseCount;
    }

    public String getNotificationCount() {
        return notificationCount;
    }

    public void setNotificationCount(String notificationCount) {
        this.notificationCount = notificationCount;
    }

    public String getInboxCount() {
        return inboxCount;
    }

    public void setInboxCount(String inboxCount) {
        this.inboxCount = inboxCount;
    }

    public String getMyCaseCount() {
        return myCaseCount;
    }

    public void setMyCaseCount(String myCaseCount) {
        this.myCaseCount = myCaseCount;
    }
}
