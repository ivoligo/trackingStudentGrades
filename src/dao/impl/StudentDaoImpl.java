package dao.impl;

import dao.StudentDao;
import model.Student;

import java.util.*;

public class StudentDaoImpl implements StudentDao {

    public static Set<Student> studentsGrades = new HashSet<>();

    @Override
    public void addStudent(Student student) {
        studentsGrades.add(student);
    }

    @Override
    public void delete(String name) {
        var student = find(name).isPresent() ? find(name).get() : null;
        studentsGrades.remove(student);
    }

    @Override
    public void updateGrade(String name, int indexOfGrade, int grade) {
        var st = find(name).isPresent() ? find(name).get() : null;
        if(st != null) {
            studentsGrades.remove(st);
            var grades = st.getGrades();
            grades.add(indexOfGrade, grade);
            st.setGrades(grades);
            addStudent(st);
        }
    }

    @Override
    public Collection<Student> findAll() {
        return studentsGrades;
    }

    @Override
    public Optional<Student> find(String name) {
        return studentsGrades.stream().filter(student -> student.getName().equals(name)).findFirst();
    }

    @Override
    public void addGradeForStudent(String name, Integer grade) {
        var stOpt = find(name);
        if (stOpt.isPresent()) {
            var st = stOpt.get();
            st.getGrades().add(grade);
        } else {
            System.out.println("Студент не найден");
        }
    }
}
