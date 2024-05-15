package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Student {

    private UUID id;

    private Fio fio;

    private List<Grade> grades = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Fio getFio() {
        return fio;
    }

    public void setFio(Fio fio) {
        this.fio = fio;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fio=" + fio +
                ", grades=" + grades +
                '}';
    }
}
