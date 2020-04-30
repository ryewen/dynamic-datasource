package com.loststars.dynamic.datasource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loststars.dynamic.datasource.dao.StudentDAO;
import com.loststars.dynamic.datasource.pojo.Student;
import com.loststars.dynamic.datasource.util.MultipleDataSourceHelper;

@Service
public class StudentService {
    
    @Autowired
    private StudentDAO studentDAO;

    public List<Student> listStudents() {
        MultipleDataSourceHelper.set(MultipleDataSourceHelper.SLAVE);
        return studentDAO.listStudents();
    }
    
    @Transactional
    public void addStudent(Student student) throws RuntimeException {
        MultipleDataSourceHelper.set(MultipleDataSourceHelper.MASTER);
        studentDAO.addStudent(student);
        //throw new RuntimeException();
    }
}
