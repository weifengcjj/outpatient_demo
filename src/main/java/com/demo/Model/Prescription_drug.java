package com.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Prescription_drug {
    //处方药品实体类

    private int id;

    private Order oid;//预约订单id

    private Doctor doctor;//医生
    
    private Drug drug;//药品
    
    private String goods;//医生建议

    public Prescription_drug(Order oid, Doctor doctor, Drug drug, String goods) {
        this.oid = oid;
        this.doctor = doctor;
        this.drug = drug;
        this.goods = goods;
    }
}
