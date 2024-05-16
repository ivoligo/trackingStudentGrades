package dao;

import model.Student;

import java.util.*;

public interface StudentDao {

    void addStudent(Student Student);

    void delete(UUID id);

    void updateGrade(UUID id, int indexOfGrade, int grade);

    Collection<Student> findAll();

    Optional<Student> find(UUID id);
}
