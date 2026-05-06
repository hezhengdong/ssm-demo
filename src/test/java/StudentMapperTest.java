import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.StudentMapper;
import org.example.entity.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StudentMapperTest {
    private SqlSession sqlSession;
    private StudentMapper studentMapper;

    // 测试前初始化SqlSession
    @Before
    public void init() throws IOException {
        // 读取配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        // 创建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        // 开启SqlSession（自动提交事务）
        sqlSession = factory.openSession(true);
        // 获取Mapper代理对象
        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    // 测试新增
    @Test
    public void testAddStudent() {
        Student student = new Student(null, "张三", 20, "男", "2023001", "北京", 1);
        int rows = studentMapper.addStudent(student);
        System.out.println("新增成功：" + rows + "行");
    }

    // 测试按id查询（需先执行新增）
    @Test
    public void testGetStudentById() {
        Student student = studentMapper.getStudentById(1);
        System.out.println("查询结果：" + student);
    }

    // 测试模糊查询、组合查询、修改、删除（写法类似，逐个运行）
    @Test
    public void testGetStudentsByName() {
        List<Student> list = studentMapper.getStudentsByName("张");
        list.forEach(System.out::println);
    }

    @Test
    public void testGetStudentsByCondition() {
        Student condition = new Student();
        condition.setName("张");
        condition.setAddress("北京");
        List<Student> list = studentMapper.getStudentsByCondition(condition);
        list.forEach(System.out::println);
    }

    // 测试后关闭SqlSession
    @After
    public void close() {
        sqlSession.close();
    }
}