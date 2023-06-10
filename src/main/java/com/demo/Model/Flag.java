package com.demo.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Flag {

    private int id=1;//状态id

    private String flagname;//状态名称

    private String flagbutton;//状态按钮内容


}
