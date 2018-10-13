// Samuel Westigard
// Student.java
// Implementation of Student class
// 10/12/2018
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Student {
    private int ID;
    private String name;
    private double average;
    private HashSet<Integer> courses = new HashSet<>();
    private HashMap<Integer, Double> grades = new HashMap<>();


    public Student(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public boolean checkAlreadyEnrolled(int courseNum) {
        if (courses.contains(courseNum)) {
            return true;
        }
        else {
            return false;
        }
    }
    public String getName() {
        return name;
    }
    public void addCourse(int courseNum) {
        courses.add(courseNum);
    }
    public void removeCourse(int courseNum) {
        courses.remove(courseNum);
    }
    public HashSet<Integer> getCourses() {
        return courses;
    }
    public void dropClasses() {
        courses.clear();
    }
    public void assignGrade(Integer courseNum, double studentGrade) {
        grades.put(courseNum, studentGrade);
        updateAverage();
    }
    public String toString() {
        return (
                "Student Number: " + ID
                + "\nName: " + name
                + "\nCourses Enrolled: "
                + formatForToString()
                + "\nAverage: " + average
                );
    }
    private String formatForToString() {
        String formattedClasses = "";
        for (Map.Entry<Integer, Double> entry : grades.entrySet()) {
            formattedClasses += "\n\t" + entry.getKey() + ": " + entry.getValue();
        }
        return formattedClasses;
    }
    private void updateAverage() {
        double sum = 0;
        for (Map.Entry<Integer, Double> entry : grades.entrySet()) {
            sum += entry.getValue();
        }
        average = sum / grades.size();
    }
}
