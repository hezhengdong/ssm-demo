import org.example.config.SpringConfig;
import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StudentServiceTest {
    public static void main(String[] args) {
        // 1. 手动加载Spring容器（核心：模拟Spring运行环境，初始化IOC容器）
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // 2. 从容器中获取StudentService实例（依赖注入已自动完成）
        StudentService studentService = context.getBean(StudentService.class);

        // 3. 测试场景1：查询存在的学生ID（需提前在数据库插入测试数据）
        testExistId(studentService);

        // 4. 测试场景2：查询不存在的学生ID
        testNonExistId(studentService);

        // 5. 关闭容器（释放资源，可选）
        context.close();
    }

    // 测试存在的ID
    private static void testExistId(StudentService service) {
        int testId = 1; // 数据库中已存在的ID
        Student result = service.getStudentById(testId);

        System.out.println("=== 测试存在的学生ID ===");
        if (result != null) {
            System.out.println("测试通过！查询结果：");
            System.out.println("ID：" + result.getId() + "，姓名：" + result.getName() + "，年龄：" + result.getAge());
        } else {
            System.out.println("测试失败！未查询到ID为" + testId + "的学生（检查数据库数据或配置）");
        }
    }

    // 测试不存在的ID
    private static void testNonExistId(StudentService service) {
        int testId = 999; // 肯定不存在的ID
        Student result = service.getStudentById(testId);

        System.out.println("\n=== 测试不存在的学生ID ===");
        if (result == null) {
            System.out.println("测试通过！查询ID为" + testId + "的学生，返回null（符合预期）");
        } else {
            System.out.println("测试失败！查询不存在的ID却返回了数据：" + result.getName());
        }
    }
}