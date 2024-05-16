package service;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import model.Student;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.UUID;

public class StudentService {

    private final StudentDao studentDao = new StudentDaoImpl();

    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    public void deleteStudent(String id) {

        studentDao.delete(UUID.fromString(id));
    }

    public void update(String id, int index, int grade) {
        System.out.println(index + " " + grade);
        studentDao.updateGrade(UUID.fromString(id), index - 1, grade);
    }

    public Collection<Student> getAllStudents() {
        return studentDao.findAll();
    }

    public void getStudent(String id) {
        if(studentDao.find(UUID.fromString(id)).isPresent()){
            studentDao.find(UUID.fromString(id)).get();
        } else {
            System.out.println("Ученик с таким id отсутствует");
        }
    }
}
