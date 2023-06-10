package com.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    private int id;

    private String username;//用户

    private String password;//密码

    private String sex;//性别

    private int age;//年龄

    private String name;//姓名

    private String title;//职位

    private Type type;//部门
}
