package com.example.StudentManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.StudentManagement.entity.Student;
import com.example.StudentManagement.service.StudentService;

@Controller
public class StudentController {
    
    private StudentService studentService;

    public StudentController(StudentService studentService){
        super();
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listStudents(Model model){
        model.addAttribute("students", studentService.getAllStudents());

        return "students";
    }

    // add student form
    @GetMapping("/student/new")
    public String createStudentForm(Model model){
        
        // create student object to hold form data
        Student student = new Student();
        model.addAttribute("student", student);

        return "create_student";
    }

    // save student
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);

        return "redirect:/students";
    }

    // get edit page
    @GetMapping("/student/edit/{id}")
    public String editStudentForm(@PathVariable("id") Long id,Model model) throws Exception{
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);

        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String upudate(@PathVariable("id") Long id,@ModelAttribute("student") Student student,Model model) throws Exception{
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());

        // update student
        studentService.updateStudent(existingStudent);

        return "redirect:/students";
    }

    // delete student
    @GetMapping("/student/{id}")
    public String delete(@PathVariable("id") Long id) throws Exception{
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
