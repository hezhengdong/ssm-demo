package org.example.controller;

import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // 前后端分离用这个，自动 JSON 序列化
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 新增学生
     * POST /api/students
     * Body: {"name":"张三","age":20,"gender":"男","number":"2023001","address":"北京","status":1}
     */
    @PostMapping
    public Integer add(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    /**
     * 修改学生
     * PUT /api/students
     * Body: {"id":1,"name":"李四","age":21,"gender":"女","number":"2023002","address":"上海","status":1}
     */
    @PutMapping
    public Integer update(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    /**
     * 删除学生
     * DELETE /api/students/1
     */
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable("id") Integer id) {
        return studentService.deleteStudent(id);
    }

    /**
     * 按ID查询
     * GET /api/students/1
     */
    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") Integer id) {
        return studentService.getStudentById(id);
    }

    /**
     * 按姓名模糊查询
     * GET /api/students/search/name?name=张
     */
    @GetMapping("/search/{name}")
    public List<Student> getByName(@PathVariable("name") String name) {
        return studentService.getStudentByName(name);
    }

    /**
     * 组合条件查询
     * POST /api/students/search
     * Body: {"name":"张","address":"北京"}
     */
    @PostMapping("/search")
    public List<Student> getByCondition(@RequestBody Student student) {
        return studentService.getStudentByCondition(student);
    }
}
