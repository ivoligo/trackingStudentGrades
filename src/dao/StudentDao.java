package dao;

import model.Student;

import java.util.*;

public interface StudentDao {

    void addStudent(Student Student);

    void delete(String name);

    void updateGrade(String name, int indexOfGrade, int grade);

    Collection<Student> findAll();

    Optional<Student> find(String name);

    void addGradeForStudent(String name, Integer grade);
}
