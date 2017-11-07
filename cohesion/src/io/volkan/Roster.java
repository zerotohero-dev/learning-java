package io.volkan;

import java.util.ArrayList;
import java.util.List;

public class Roster {
    private int capacity;
    private List<String> students;

    public Roster(int max) {
        capacity = max;
        students = new ArrayList<>();
    }

    public void enrollStudent(String name) {
        students.add(name);
    }

    public boolean enrollStudentConditionally(String name) {
        if (students.size() >= capacity) {return false;}

        enrollStudent(name);
        return true;
    }
}
