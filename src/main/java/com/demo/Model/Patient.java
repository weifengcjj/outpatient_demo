package com.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private int id;

    private String name;//病人名字

    private String sex;//性别

    private int age;//年龄
}
