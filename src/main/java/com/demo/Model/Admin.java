package com.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {

    private int id;//id

    private String username;//用户

    private String password;//密码

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
