package org.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.example.entity.Student;

import java.util.List;

public interface StudentMapper {
    // 新增
    @Insert("INSERT INTO students(name, age, gender, number, address, status) " +
            "VALUES(#{name}, #{age}, #{gender}, #{number}, #{address}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addStudent(Student student);

    // 修改
    int updateStudent(Student student);

    // 删除（按id）
    @Delete("DELETE FROM students WHERE id = #{id}")
    int deleteStudent(Integer id);

    // 按id查询
    @Select("SELECT * FROM students WHERE id = #{id}")
    Student getStudentById(Integer id);

    // 模糊查询（按姓名）
    @Select("SELECT * FROM students WHERE name LIKE concat('%', #{name}, '%')")
    List<Student> getStudentsByName(String name);

    // 组合查询（姓名+住址，支持单独/同时传参）
    List<Student> getStudentsByCondition(Student student);
}