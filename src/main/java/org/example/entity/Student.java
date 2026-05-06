package org.example.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String number;
    private String address;
    private Integer status;

    // 全参/无参构造、getter/setter、toString()
}
