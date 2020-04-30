package com.loststars.dynamic.datasource.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.loststars.dynamic.datasource.DynamicDatasourceApplication;
import com.loststars.dynamic.datasource.pojo.Student;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicDatasourceApplication.class)
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;
    
    @Test
    public void listTest() {
        List<Student> students = studentService.listStudents();
        students.forEach((student) -> System.out.println(student.getId() + " " + student.getName() + " " + student.getAge()));
    }
    
    @Test
    public void addTest() {
        Student student = new Student();
        student.setAge(40);
        student.setName("WLX");
        try {
            studentService.addStudent(student);
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
