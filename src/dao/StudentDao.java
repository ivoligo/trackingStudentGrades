package dao;

import model.Student;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface StudentDao {

    void addStudent(Student Student);

    void delete(UUID id);

    void updateGrade(UUID id, String subject, int indexOfGrade, Integer grade);

    Collection<Student> findAll();

    Student find(UUID id);
}
