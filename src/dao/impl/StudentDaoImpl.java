package dao.impl;

import dao.StudentDao;
import model.Student;

import java.util.*;

public class StudentDaoImpl implements StudentDao {

    public static final Set<Student> studentsGrades = new HashSet<>();

    @Override
    public void addStudent(Student student) {
        studentsGrades.add(student);
    }

    @Override
    public void delete(UUID id) {
        var student = find(id).isPresent() ? find(id).get() : null;
        studentsGrades.remove(student);
    }

    @Override
    public void updateGrade(UUID id, int indexOfGrade, int grade) {
        var st = find(id).isPresent() ? find(id).get() : null;
        if(st != null) {
            studentsGrades.remove(st);
            var grades = st.getGrades();
            grades.add(indexOfGrade, grade);
            System.out.println(indexOfGrade + " " + grade);
            st.setGrades(grades);
            addStudent(st);
        }
    }

    @Override
    public Collection<Student> findAll() {
        return studentsGrades;
    }

    @Override
    public Optional<Student> find(UUID id) {
        return studentsGrades.stream().filter(student -> student.getId().equals(id)).findFirst();
    }
}
