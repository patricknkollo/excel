package com.excel.export.excel.services;

import com.excel.export.excel.entities.Student;
import com.excel.export.excel.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Component
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepo;

    public void addStudent(Student student) {
        studentRepo.save(student);
    }
    public List< Student > getTheListStudent() {
        Student student1 = new Student(1L, "patrick", "nkollo@essen.de", "77");
        Student student2 = new Student(2L, "pipi", "mbo@essen.de", "65");
        List< Student > result = Arrays.asList(student1, student2);
        //return studentRepo.findAll();
        return result;
    }
}
