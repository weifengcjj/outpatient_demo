package com.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private int id;//订单id

    private User user;//订单用户

    private Type type;//科室类型

    private String goods;//病情简述

    private  String time;//创建时间

    private String orderid;//唯一订单号

    private Flag flag;//状态


}
