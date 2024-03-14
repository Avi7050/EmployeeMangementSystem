package com.student.StudentManagementSystem.rest;

import com.student.StudentManagementSystem.entity.Student;
import com.student.StudentManagementSystem.service.StudentService;
import com.student.StudentManagementSystem.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private StudentService studentService;
    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping("/employees")
    public Student save(@RequestBody Student theStudent){
        Student student = studentService.save(theStudent);
        return student;
    }
    @GetMapping("/employees/{id}")
    public Student findById(@PathVariable int id){
        return studentService.findById(id);
    }
    @GetMapping("/employees")
    public List<Student> findAll(){
        List<Student> students = studentService.findAll();
        return students;
    }
    @PutMapping("/employees")
    public Student update(@RequestBody Student student){
        return studentService.update(student);
    }
    @DeleteMapping("/employees/{id}")
    public String deleteById(@PathVariable int id){
        studentService.deleteById(id);
        return "Student deleted: " + id;
    }
    @DeleteMapping("/employees")
    public String deleteAll(){
        studentService.deleteAll();
        return "All Students deleted";
    }
}
