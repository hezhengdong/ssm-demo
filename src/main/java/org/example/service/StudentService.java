package org.example.service;

import org.example.entity.Student;
import org.example.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    // 增删改、三个查

    public Integer addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    public Integer updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    public Integer deleteStudent(Integer id) {
        return studentMapper.deleteStudent(id);
    }

    public Student getStudentById(int id) {
        return studentMapper.getStudentById(id);
    }

    public List<Student> getStudentByName(String name) {
        return studentMapper.getStudentsByName(name);
    }

    public List<Student> getStudentByCondition(Student student) {
        return studentMapper.getStudentsByCondition(student);
    }
}
