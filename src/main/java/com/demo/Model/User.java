package com.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {


    private int id;//id

    private String username;//用户

    private String password;//密码

    private String time;//创建时间

    private String name;//病人名字

    private String sex;//性别

    private int age;//年龄

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
