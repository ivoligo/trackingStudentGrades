package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student {

    private UUID id;

    private String name;

//    private String surname;
//
//    private String patronymic;

    private List<Integer> grades = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", grades=" + grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }
}
