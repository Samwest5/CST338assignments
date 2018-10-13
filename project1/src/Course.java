// Samuel Westigard
// Course.java
// Implementation of course class
// 10/12/2018
import java.util.HashMap;
import java.util.Map;

public class Course {
    private int courseNumber;
    private String courseTitle;
    private int maxEnrollment;
    private int enrollment;
    private String instructor;
    private int employeeNumber;
    private String location;
    private double courseAverage;
    private HashMap<Integer, Double> gradeBook = new HashMap<>();

    public Course(int courseNumber, String courseTitle, int maxEnrollment, String location) {
        this.courseNumber = courseNumber;
        this.courseTitle = courseTitle;
        this.maxEnrollment = maxEnrollment;
        enrollment = 0;
        this.location = location;
        courseAverage = 0;
        instructor = "";
        employeeNumber = -1;
    }
    public int getCourseNumber() {
        return courseNumber;
    }
    public String getCourseTitle() {
        return courseTitle;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getMaxEnrollment() {
        return maxEnrollment;
    }
    public int getEnrollment() {
        return enrollment;
    }
    public String getInstructor() {
        return instructor;
    }
    public int getEmployeeNumber() {
        return employeeNumber;
    }
    public double getCourseAverage() {
        return courseAverage;
    }
    public void addStudent(Integer studentID, double grade) {
        gradeBook.put(studentID, grade);
        increaseEnrollment();
        updateAverage();
    }
    public void removeStudent(Integer studentID) {
        gradeBook.remove(studentID);
        decreaseEnrollment();
        updateAverage();
    }
    public void changeScore(Integer studentId, double newScore) {
        gradeBook.put(studentId, newScore);
        updateAverage();
    }
    public void updateLocation(String location){
        this.location = location;
    }
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public void setInstructorEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
    public double getGrade(int studentID) {
        return gradeBook.get(studentID);
    }
    private void updateAverage() {
        double sum = 0;
        for (Map.Entry<Integer, Double> grade : gradeBook.entrySet()) {
            sum += grade.getValue();
        }
        courseAverage = sum / gradeBook.size();
    }
    private void increaseEnrollment() {
        enrollment += 1;
    }
    private void decreaseEnrollment() {
        enrollment -= 1;
    }
}

