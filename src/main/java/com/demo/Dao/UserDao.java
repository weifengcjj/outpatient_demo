package com.demo.Dao;

import com.demo.Model.Admin;
import com.demo.Model.Doctor;
import com.demo.Model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface UserDao {
    User login(@Param("username") String username,@Param("password") String password);//登录

    int insert(@Param("user") User user);//注册

    Admin adminlogin(@Param("username") String username,@Param("password") String password);//管理员登录

    Doctor doctorlogin(@Param("username") String username,@Param("password") String password);//医生登录


}
