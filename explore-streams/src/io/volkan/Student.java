package io.volkan;

public class Student {
    private int studentId;
    private String firstName;
    private String lastName;

    public Student(int id, String fName, String lName) {
        studentId = id;
        firstName = fName;
        lastName = lName;
    }

    public int getStudentId() {return studentId;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getFullName() {return String.format("%s %s", firstName, lastName);}
}
