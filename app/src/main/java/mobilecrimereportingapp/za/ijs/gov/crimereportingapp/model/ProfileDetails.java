package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

/**
 * Created by JimmyM on 2017/07/14.
 */

public class ProfileDetails {

    private String username = "jimmy", role = "admin", deviceID;

    public ProfileDetails() {
    }

    public ProfileDetails(String username, String role, String deviceID) {
        this.username = username;
        this.role = role;
        this.deviceID = deviceID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }
}
