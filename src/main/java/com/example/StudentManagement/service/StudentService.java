package com.example.StudentManagement.service;

import java.util.List;

import com.example.StudentManagement.entity.Student;


public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudentById(Long id) throws Exception;

    Student updateStudent(Student student);
    
    void deleteStudent(Long id) throws Exception;
}
