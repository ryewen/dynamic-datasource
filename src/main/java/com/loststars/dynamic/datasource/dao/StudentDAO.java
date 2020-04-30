package com.loststars.dynamic.datasource.dao;

import java.util.List;

import com.loststars.dynamic.datasource.pojo.Student;

public interface StudentDAO {

    public List<Student> listStudents();
    
    public void addStudent(Student student);
}
