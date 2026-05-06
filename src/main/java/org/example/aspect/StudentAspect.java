package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.entity.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect      // 标识为切面类
@Component   // 注册为 Spring Bean
public class StudentAspect {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 切入点：匹配 addStudent 方法
     */
    @Pointcut("execution(* org.example.service.StudentService.addStudent(..))")
    public void addStudentPointcut() {}

    /**
     * 切入点：匹配 deleteStudent 方法
     */
    @Pointcut("execution(* org.example.service.StudentService.deleteStudent(..))")
    public void deleteStudentPointcut() {}

    /**
     * 环绕通知：添加学生
     */
    @Around("addStudentPointcut()")
    public Object aroundAddStudent(ProceedingJoinPoint joinPoint) throws Throwable {
        // ========== 前置：校验操作 ==========
        System.out.println("================== AOP 前置校验 ==================");
        System.out.println("[校验] 时间: " + LocalDateTime.now().format(FORMATTER));
        System.out.println("[校验] 方法: " + joinPoint.getSignature().getName());

        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof Student student) {
            // 校验学生信息
            if (student.getName() == null || student.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("学生姓名不能为空！");
            }
            if (student.getAge() == null || student.getAge() < 0 || student.getAge() > 150) {
                throw new IllegalArgumentException("学生年龄不合法！");
            }
            System.out.println("[校验] 参数校验通过: " + student);
        }

        // ========== 执行目标方法 ==========
        Object result = joinPoint.proceed();

        // ========== 后置：日志处理 ==========
        System.out.println("================== AOP 后置日志 ==================");
        System.out.println("[日志] 时间: " + LocalDateTime.now().format(FORMATTER));
        System.out.println("[日志] 操作: 添加学生");
        System.out.println("[日志] 结果: " + (((Integer) result) > 0 ? "成功" : "失败"));
        System.out.println("==================================================\n");

        return result;
    }

    /**
     * 环绕通知：删除学生
     */
    @Around("deleteStudentPointcut()")
    public Object aroundDeleteStudent(ProceedingJoinPoint joinPoint) throws Throwable {
        // ========== 前置：校验操作 ==========
        System.out.println("================== AOP 前置校验 ==================");
        System.out.println("[校验] 时间: " + LocalDateTime.now().format(FORMATTER));
        System.out.println("[校验] 方法: " + joinPoint.getSignature().getName());

        Object[] args = joinPoint.getArgs();
        Integer studentId = (Integer) args[0];

        // 校验 ID
        if (studentId == null || studentId <= 0) {
            throw new IllegalArgumentException("学生ID不合法！");
        }
        System.out.println("[校验] 待删除学生ID: " + studentId + " - 校验通过");

        // ========== 执行目标方法 ==========
        Object result = joinPoint.proceed();

        // ========== 后置：日志处理 ==========
        System.out.println("================== AOP 后置日志 ==================");
        System.out.println("[日志] 时间: " + LocalDateTime.now().format(FORMATTER));
        System.out.println("[日志] 操作: 删除学生 (ID=" + studentId + ")");
        System.out.println("[日志] 结果: " + (((Integer) result) > 0 ? "成功" : "失败(记录不存在)"));
        System.out.println("==================================================\n");

        return result;
    }
}
