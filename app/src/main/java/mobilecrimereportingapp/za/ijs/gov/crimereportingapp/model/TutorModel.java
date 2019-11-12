package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TutorModel {

    private String Email;
    private String Password;
    private String PhoneNumber;
    private String FullName;
    private String Province;
    private String Area;
    private GradeModel Grade;
    private List<SubjectModel> Subjects;
    private List<String> Students;
    private String PreferredTutoringMethod;

    public TutorModel(){
    }
    public TutorModel(String FullName){
        this.FullName = FullName;
    }

    public TutorModel(String Email, String Password, String PhoneNumber, String FullName, String Province, String Area, GradeModel Grade, List<SubjectModel> Subjects, List<String> Students, String PreferredTutoringMethod) {
        this.Email = Email;
        this.Password = Password;
        this.PhoneNumber = PhoneNumber;
        this.FullName = FullName;
        this.Province = Province;
        this.Area = Area;
        this.Grade = Grade;
        this.Subjects = Subjects;
        this.Students = Students;
        this.PreferredTutoringMethod = PreferredTutoringMethod;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    public GradeModel getGrade() {
        return Grade;
    }

    public void setGrade(GradeModel Grade) {
        this.Grade = Grade;
    }

    public List<SubjectModel> getSubjects() {
        return Subjects;
    }

    public void setSubjects(List<SubjectModel> Subjects) {
        this.Subjects = Subjects;
    }

    public List<String> getStudents() {
        return Students;
    }

    public void setStudents(List<String> Students) {
        this.Students = Students;
    }

    public String getPreferredTutoringMethod() {
        return PreferredTutoringMethod;
    }

    public void setPreferredTutoringMethod(String _PreferredTutoringMethod) {
        this.PreferredTutoringMethod = _PreferredTutoringMethod;
    }


    public JSONObject toJSON() {

        JSONObject jo = new JSONObject();
        try {
            jo.put("Email", getEmail());
            jo.put("Password", getPassword());
            jo.put("PhoneNumber", getPhoneNumber());
            jo.put("FullName", getFullName());
            jo.put("Province", getProvince());
            jo.put("Area", getArea());
            jo.put("Grades", getGrade());
            jo.put("Subjects", getSubjects());
            jo.put("PreferredTutoringMethod", getPreferredTutoringMethod());
            jo.put("Students", getStudents());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jo;
    }
}
