package com.student.StudentManagementSystem.service;

import com.student.StudentManagementSystem.entity.Student;

import java.util.List;

public interface StudentService {

    Student save(Student theStudent);
    Student findById(int theId);
    List<Student> findAll();
    Student update(Student student);
    void deleteById(int id);
    void deleteAll();
}
