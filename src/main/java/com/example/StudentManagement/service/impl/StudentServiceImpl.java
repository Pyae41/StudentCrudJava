package com.example.StudentManagement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.StudentManagement.entity.Student;
import com.example.StudentManagement.exception.StudentNotFoundException;
import com.example.StudentManagement.repository.StudentRepository;
import com.example.StudentManagement.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{


    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) throws Exception{
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id "+id +" is not exist."));
        return student;
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) throws Exception{
        Student existStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id "+id +" is not exist."));

        studentRepository.delete(existStudent);
    }
    
}
