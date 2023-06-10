package com.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drug {

    private int id;

    private String name;//药品名称

    private String specification;//规格

    private double price;//价格

    private int stock;//库存
}
