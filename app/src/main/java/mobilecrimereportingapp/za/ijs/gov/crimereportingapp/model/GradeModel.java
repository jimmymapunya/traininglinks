package mobilecrimereportingapp.za.ijs.gov.crimereportingapp.model;

public class GradeModel {

    private int GradeId;
    private String GradeName;

    public GradeModel() {
    }
    public GradeModel(int GradeId, String GradeName) {
        this.GradeId = GradeId;
        this.GradeName = GradeName;
    }
    public int getGradeId() {
        return GradeId;
    }

    public void setGradeId(int GradeId) {
        this.GradeId = GradeId;
    }

    public String getGradeName() {
        return GradeName;
    }

    public void setGradeName(String GradeName) {
        this.GradeName = GradeName;
    }
}
