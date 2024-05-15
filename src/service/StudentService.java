package service;

import model.Grade;
import model.Student;

import java.util.Collection;

public interface StudentService {

    void addStudent(Student student);
    void deleteStudent(Student student);
    void update(Student student, Grade grade);
    Collection<Student> getAllStudents();
    void getStudent(Student student);

}
