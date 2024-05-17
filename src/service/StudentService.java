package service;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import model.Student;

import java.util.Collection;

public class StudentService {

    private final StudentDao studentDao = new StudentDaoImpl();

    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    public void deleteStudent(String name) {

        studentDao.delete(name);
    }

    public void addGrade(String name, String grade){
        studentDao.addGradeForStudent(name, Integer.valueOf(grade));
    }

    public void update(String name, int index, int grade) {
        System.out.println(index + " " + grade);
        studentDao.updateGrade(name, index - 1, grade);
    }

    public Collection<Student> getAllStudents() {
        return studentDao.findAll();
    }

    public void getStudent(String name) {
        if (studentDao.find(name).isPresent()) {
            studentDao.find(name).get();
        } else {
            System.out.println("Ученик с таким именем отсутствует");
        }
    }
}
