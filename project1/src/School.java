// Samuel Westigard
// School.Java
// School class handles Course Instructor and Student
// 10/12/2018
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class School {

    HashMap<Integer, Instructor> instructors = new HashMap<>();
    HashMap<Integer, Course> courses = new HashMap<>();
    HashMap<Integer, Student> students = new HashMap<>();
    // allows to search by email
    HashMap<String, Integer> emailToInstructor = new HashMap<>();
    private int numCourses;
    private int numStudents;
    private int numInstructors;
    private String name;

    public School(String name) {
        this.name = name;
        numCourses = 0;
        numStudents = 0;
        numInstructors = 0;
    }
    public void readData(String filePath) {
        try {
            File file = new File(filePath);
            Scanner input = new Scanner(file);
            int n = input.nextInt();
            input.nextLine();
            for (int i = 0; i < n; i++) {
                String instructorInfo = input.nextLine();
                String[] pieces = instructorInfo.split(",", -1);
                addInstructor(Integer.parseInt(pieces[0]), pieces[1], pieces[2], pieces[3]);
            }
            n = input.nextInt();
            input.nextLine();
            for (int i = 0; i < n; i++) {
                String courseInfo = input.nextLine();
                String[] pieces = courseInfo.split(",", -1);
                addCourse(Integer.parseInt(pieces[0]), pieces[1], Integer.parseInt(pieces[2]), pieces[3]);
            }
            n = input.nextInt();
            input.nextLine();
            for (int i = 0; i < n; i++) {
                String studentInfo = input.nextLine();
                String[] pieces = studentInfo.split(",", -1);
                addStudent(Integer.parseInt(pieces[0]), pieces[1]);
            }
            System.out.println("Done");
        }
        catch (Exception e){
        }
    }
    public void addInstructor(int employeeNumber, String name, String email, String phoneNumber) {
        if (instructors.get(employeeNumber) == null) {
            instructors.put(employeeNumber, new Instructor(employeeNumber, name, email, phoneNumber));
            emailToInstructor.put(email, employeeNumber);
            numInstructors += 1;
        }
        else {
            System.out.println("Instructor: " + employeeNumber + " already exists");
        }
    }
    public void addCourse(int courseNumber, String courseTitle, int maxEnrollment, String location) {
        if (courses.get(courseNumber) == null) {
            courses.put(courseNumber, new Course(courseNumber, courseTitle, maxEnrollment, location));
            numCourses += 1;
        }
        else {
            System.out.println("Course: " + courseNumber + " already exists");
        }
    }
    public void addStudent(int ID, String name) {
        if (students.get(ID) == null) {
            students.put(ID, new Student(ID, name));
            numStudents += 1;
        }
        else {
            System.out.println("Student: " + ID + " already exists");
        }
    }
    public void assignInstructor(int courseNumber, int employeeNumber) {

        Instructor instructor = instructors.get(employeeNumber);
        Course course = courses.get(courseNumber);

        if (instructor == null) {
            System.out.println("Instructor: " + employeeNumber + " does not exist");
            return;
        }
        if (course == null) {
            System.out.println("Course: " + courseNumber + " does not exist");
        }
        else if (course.getInstructor() != "" || course.getInstructor().equals(instructor.getName())) {
            String name = course.getInstructor();
            System.out.println(name + " already assigned to Course: " + courseNumber);
        }
        else {
            course.setInstructor(instructor.getName());
            course.setInstructorEmployeeNumber(employeeNumber);
            instructor.addCourse(courseNumber);
        }
    }
    public void register(int courseNumber, int studentID) {
        Course course = courses.get(courseNumber);
        Student student = students.get(studentID);
        if (course == null || student == null) {
            System.out.println("Either Course " + courseNumber + " or Student " + studentID + " does not exist");
        }
        else if (course.getEnrollment() == course.getMaxEnrollment()) {
            System.out.println("Course: " + courseNumber + " is full");
        }
        else if (student.checkAlreadyEnrolled(courseNumber)) {
            System.out.println("Student: " + studentID + " already enrolled in Course: " + courseNumber);
        }
        else {
            course.addStudent(studentID, 100);
            student.addCourse(courseNumber);
        }
    }
    public void unRegister(int courseNumber, int studentID) {
        Course course = courses.get(courseNumber);
        Student student = students.get(studentID);
        if (course == null || student == null) {
            System.out.println("Either Course: " + courseNumber + " or Student: " + studentID + " does not exist");
        }
        else if (!student.checkAlreadyEnrolled(courseNumber)) {
            System.out.println("Student: " + studentID + " is not enrolled in Course: " + courseNumber);
        }
        else {
            course.removeStudent(studentID);
            student.removeCourse(courseNumber);
        }
    }
    public void deleteCourse(int courseNumber) {
        Course course = courses.get(courseNumber);
        if (course == null) {
            System.out.println("Course: " + courseNumber + " does not exist");
        }
        else if (course.getEnrollment() != 0) {
            System.out.println("Can't delete. Course: " + courseNumber + " has students enrolled");
        }
        else {
            int instructorNumber = course.getEmployeeNumber();
            if (instructorNumber != -1) {
                Instructor instructor = instructors.get(instructorNumber);
                instructor.removeCourse(courseNumber);
            }
            courses.remove(courseNumber);
        }
    }
    public void putScore(int courseNumber, int ID, double grade) {
        Course course = courses.get(courseNumber);
        Student student = students.get(ID);
        if (course == null) {
            System.out.println("Course : " + courseNumber + " does not exist");
        }
        else if (student == null) {
            System.out.println("Student " + ID + " does not exist");
        }
        else {
            HashSet<Integer> studentCourses = student.getCourses();
            if (!studentCourses.contains(courseNumber)) {
                System.out.println("Student: " + ID + " not enrolled in " + "Course: " + courseNumber);
            }
            else {
                course.changeScore(ID, grade);
            }
        }
    }
    public void searchByEmail(String email) {
        System.out.println("Search key: " + email);
        if (emailToInstructor.get(email) == null) {
            System.out.println("No employee with email " + email);
        }
        else {
            Instructor instructor = instructors.get(emailToInstructor.get(email));
            int number = instructor.getEmployeeNumber();
            String name = instructor.getName();
            String phone = instructor.getPhoneNumber();
            System.out.println("Employee Number: " + number);
            System.out.println("Name: " + name);
            System.out.println("Phone: " + phone);
        }
    }
    public void graduateStudent(int ID) {
        if (students.get(ID) == null) {
            System.out.println("Student: " + ID + " does not exist");
        }
        else {
            Student student = students.get(ID);
            HashSet<Integer> studentCourses = student.getCourses();
            for (Integer courseNumber : studentCourses) {
                courses.get(courseNumber).removeStudent(ID);
            }
            student.dropClasses();
            students.remove(ID);
        }
    }
    public Student getStudent(int studentID) {
        Student student = students.get(studentID);
        if (student != null) {
            HashSet<Integer> coursesTaking = student.getCourses();
            for (Integer courseNumber : coursesTaking) {
                double grade = courses.get(courseNumber).getGrade(studentID);
                student.assignGrade(courseNumber, grade);
            }
        }
        return student;
    }
    public Instructor getInstructor(int courseNumber) {
        Course course = courses.get(courseNumber);
        String instructorName = course.getInstructor();
        Instructor instructor = null;
        for (Map.Entry<Integer, Instructor> entry : instructors.entrySet()) {
            if (entry.getValue().getName().equals(instructorName)) {
                instructor = entry.getValue();
                break;
            }
        }
        System.out.println(instructor);
        if (instructor != null) {
            HashSet<Integer> coursesTeaching = instructor.getCourses();
            for (Integer courseNumberr : coursesTeaching) {
                int enrollment = courses.get(courseNumberr).getEnrollment();
                instructor.setEnrollment(courseNumberr, enrollment);
            }
        }
        return instructor;
    }
    public void courseInfo(int courseNumber) {
        Course course = courses.get(courseNumber);
        System.out.println("Course Number: " + course.getCourseNumber());
        System.out.println("Instructor: " + course.getInstructor());
        System.out.println("Course Title: " + course.getCourseTitle());
        System.out.println("Room: " + course.getLocation());
        System.out.println("Total Enrolled: " + course.getEnrollment());
        System.out.println("Course Average: " + course.getCourseAverage());
    }
    public void courseInfo() {
        System.out.println("Number of Courses: " + courses.size());
        System.out.println(formatForCourseInfo());

    }
    public void schoolInfo() {
        System.out.println("School Name: " + name);
        System.out.print("Instructor Information: ");
        System.out.println(formatForInstructorInfo());
        System.out.print("Course Information: ");
        System.out.println(formatForCourseInfo());
        System.out.print("Student Information: ");
        System.out.println(formatForStudentInfo());
    }
    public Course getCourse(int courseNumber) {
        return courses.get(courseNumber);
    }
    private String formatForStudentInfo() {
        String formattedStudents = "";
        for (Map.Entry<Integer, Student> entry : students.entrySet()) {
            formattedStudents += "\n\t" + entry.getValue().getName();
        }
        return formattedStudents;
    }
    private String formatForInstructorInfo() {
        String formattedInstructors = "";
        for (Map.Entry<Integer, Instructor> entry : instructors.entrySet()) {
            formattedInstructors += "\n\t" + entry.getValue().getName();
        }
        return formattedInstructors;
    }
    private String formatCoursesforSchoolInfo() {
        String formattedCourses = "";
        for (Map.Entry<Integer, Course> entry : courses.entrySet()) {
            formattedCourses += "\n\t" + entry.getValue().getCourseTitle();
        }
        return formattedCourses;
    }
    private String formatForCourseInfo() {
        String formattedClasses = "";
        for (Map.Entry<Integer, Course> entry : courses.entrySet()) {
            formattedClasses += "\n\t" + entry.getKey() + ": " + entry.getValue().getEnrollment() + " enrolled";
        }
        return formattedClasses;
    }
}
