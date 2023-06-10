package com.demo.Dao;

import com.demo.Model.Doctor;
import com.demo.Model.Order;
import com.demo.Model.Prescription_drug;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DoctorServiceDao {
    List<Order>  typeOrder(int tid);//根据科室拿到相关的预约

    int createprescription(@Param("pre") Prescription_drug prescription_drug);//生成处方单

    int UpdateFlag(int oid);

    List<Prescription_drug> typeper(int tid);
}
