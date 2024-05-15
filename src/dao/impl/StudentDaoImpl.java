package dao.impl;

import dao.StudentDao;
import model.Student;

import java.util.*;

public class StudentDaoImpl implements StudentDao {

    private final Map<UUID, Student> studentsGrades = new HashMap<>();


    @Override
    public void addStudent(Student student) {
        System.out.println(student.toString());
        studentsGrades.put(student.getId(), student);
    }

    @Override
    public void delete(UUID id) {
        studentsGrades.remove(id);
    }

    @Override
    public void updateGrade(UUID id, String subject, int indexOfGrade, Integer grade) {

    }

    @Override
    public Collection<Student> findAll() {
        System.out.println(studentsGrades.values().size());
        return studentsGrades.values();
    }

    @Override
    public Student find(UUID id) {
        return studentsGrades.get(id);
    }
}
