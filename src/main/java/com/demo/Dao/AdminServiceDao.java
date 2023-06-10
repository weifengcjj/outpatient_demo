package com.demo.Dao;

import com.demo.Model.Doctor;
import com.demo.Model.Drug;
import com.demo.Model.Order;
import com.demo.Model.Prescription_drug;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminServiceDao {

    List<Order> orderAll();//管理员拿到所有订单

    List<Doctor> doctorAll();;//管理员拿到所有医生

    List<Drug> drugAll();//管理员拿到所有药品

    List<Prescription_drug> preAll();//管理员拿到所有处方单

    int insertDrug(@Param("drug") Drug drug);//管理员添加药品

}
