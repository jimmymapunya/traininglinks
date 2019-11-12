package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

public class SubjectModel {

    private int SubjectId;
    private String SubjectName;

    public SubjectModel(int SubjectId, String SubjectName) {
        this.SubjectId = SubjectId;
        this.SubjectName = SubjectName;
    }

    public int getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(int subjectId) {
        SubjectId = subjectId;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }
}
