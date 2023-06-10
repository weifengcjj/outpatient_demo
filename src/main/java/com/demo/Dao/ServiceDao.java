package com.demo.Dao;

import com.demo.Model.Order;
import com.demo.Model.Prescription_drug;
import com.demo.Model.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ServiceDao {
    List<Type> alltype();//拿到所有科室部门

    int insertorder(@Param("order") Order order);//创建订单

    List<Order> selectorderone(int uid);//根据个人id查询个人订单

    List<Prescription_drug> preone(int uid);//根据个人id查询个人处方单
}
