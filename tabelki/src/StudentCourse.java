public class StudentCourse {
    private int id;
    private int studentId;
    private int courseId;
    private float value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public StudentCourse(int id, int SId, int CId, float value){
        this.id = id;
        this.studentId = SId;
        this.courseId = CId;
        this.value = value;
    }
}
