import org.example.config.SpringConfig;
import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StudentAopTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        StudentService studentService = context.getBean(StudentService.class);
        System.out.println("========== 测试1: 添加学生 ==========\n");
        testAddStudent(studentService);
        System.out.println("========== 测试2: 删除学生 ==========\n");
        testDeleteStudent(studentService);
        System.out.println("========== 测试3: 校验失败场景 ==========\n");
        testValidationFailed(studentService);
        context.close();
    }

    private static void testAddStudent(StudentService service) {
        Student student = new Student();
        student.setName("王五");
        student.setAge(22);
        student.setGender("男");
        student.setNumber("2024001");
        student.setAddress("深圳");
        student.setStatus(1);

        Integer result = service.addStudent(student);
        System.out.println("新增学生返回值: " + result + ", 生成ID: " + student.getId() + "\n");
    }

    // 测试删除学生
    private static void testDeleteStudent(StudentService service) {
        Integer result = service.deleteStudent(1);
        System.out.println("删除学生返回值: " + result + "\n");
    }

    // 测试校验失败
    private static void testValidationFailed(StudentService service) {
        try {
            Student invalidStudent = new Student();
            invalidStudent.setName("");  // 空姓名，触发校验失败
            invalidStudent.setAge(20);
            service.addStudent(invalidStudent);
        } catch (IllegalArgumentException e) {
            System.out.println("[异常捕获] " + e.getMessage() + "\n");
        }

        try {
            service.deleteStudent(-1);  // 非法ID，触发校验失败
        } catch (IllegalArgumentException e) {
            System.out.println("[异常捕获] " + e.getMessage() + "\n");
        }
    }
}
