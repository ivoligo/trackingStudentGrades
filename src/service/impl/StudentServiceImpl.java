package service.impl;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import model.Grade;
import model.Student;
import service.StudentService;

import java.util.Collection;

public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao = new StudentDaoImpl();

    @Override
    public void addStudent(Student student) {
        System.out.println(student.toString());
        studentDao.addStudent(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentDao.delete(student.getId());
    }

    @Override
    public void update(Student student, Grade grade) {

    }

    @Override
    public Collection<Student> getAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public void getStudent(Student student) {

    }
}
