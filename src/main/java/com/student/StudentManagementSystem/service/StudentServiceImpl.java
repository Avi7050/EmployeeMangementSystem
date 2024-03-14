package com.student.StudentManagementSystem.service;

import com.student.StudentManagementSystem.Dao.StudentRepository;
import com.student.StudentManagementSystem.Exception.StudentNotFoundException;
import com.student.StudentManagementSystem.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public Student save(Student theStudent) {
        theStudent.setId(0);
        Student student = studentRepository.save(theStudent);
        return student;
    }

    @Override
    public Student findById(int theId) {
        Optional<Student> result = studentRepository.findById(theId);
        Student student = null;
        if(result.isPresent()){
            student = result.get();
        }else{
            throw new StudentNotFoundException("Student not found with id: " + theId);
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    @Override
    @Transactional
    public Student update(Student student) {
       return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Optional<Student> result = studentRepository.findById(id);
            Student student = null;
        if(result.isPresent()){
            studentRepository.deleteById(id);
        }
        else{
            throw new StudentNotFoundException("Student not found to delete with id: " +id);
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        studentRepository.deleteAll();
    }
}
