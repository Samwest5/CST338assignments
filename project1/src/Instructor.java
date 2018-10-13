// Samuel Westigard
// Instructor.java
// Implementation of Instructor Class
// 10/12/2018
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Instructor {
    private int employeeNumber;
    private String name;
    private String email;
    private String phoneNumber;
    private HashSet<Integer> courses = new HashSet<>();
    // Maps course number to number of students
    private HashMap<Integer, Integer> enrollment = new HashMap<>();

    public Instructor(int employeeNumber, String name, String email, String phoneNumber) {
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public String getName() {
        return name;
    }
    public int getEmployeeNumber() {
        return employeeNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void addCourse(int courseNumber) {
        courses.add(courseNumber);
    }
    public void removeCourse(int courseNumber) {
        courses.remove(courseNumber);
    }
    public void setEnrollment(int courseNum, int numStudents) {
        enrollment.put(courseNum, numStudents);
    }
    public HashSet<Integer> getCourses() {
        return courses;
    }
    public String toString() {
        return (
                "Instructor Number: " + employeeNumber
                + "\nName: " + name
                + "\nCourses Teaching: "
                + formatForToString()
                );
    }
    private String formatForToString() {
        String formattedClasses = "";
        for (Map.Entry<Integer, Integer> entry : enrollment.entrySet()) {
            formattedClasses += "\n\t" + entry.getKey() + ": " + entry.getValue() + " enrolled";
        }
        return formattedClasses;
    }
}
