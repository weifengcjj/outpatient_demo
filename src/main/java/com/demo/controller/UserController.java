package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.Model.Admin;
import com.demo.Model.Doctor;
import com.demo.Model.User;
import com.demo.Service.UserService;
import com.demo.util.JWTuuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")//TODO 用户登录
    @ResponseBody
    public Object login(@RequestBody JSONObject json, HttpServletResponse response, HttpServletRequest req){
        String username = json.getString("username");
        String password = json.getString("password");
        System.out.println("username----"+username);
        System.out.println("password----"+password);
        User login = userService.login(username,password);
        if(login!=null)
        {
            req.getSession().setAttribute("user",login);
            String creatjwt = JWTuuid.creatjwt(username, login);
            System.out.println("jwt码-----"+creatjwt);

            response.addHeader("Authorization","Bearer "+creatjwt);
            response.setContentType("application/json;charset=utf-8");
            return creatjwt;
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
        response.setContentType("application/json;charset=utf-8");
        return null;
    }

    @PostMapping("/adminlogin")//TODO 管理员登录
    @ResponseBody
    public Object adminlogin(@RequestBody JSONObject json, HttpServletResponse response){
        String username = json.getString("username");
        String password = json.getString("password");
        System.out.println("username--管理员--"+username);
        System.out.println("password--管理员--"+password);
        Admin adminlogin = userService.adminlogin(username,password);
        if(adminlogin!=null)
        {
            String creatjwt = JWTuuid.creatjwt(username, adminlogin);
            System.out.println("jwt码----管理员-----"+creatjwt);

            response.setContentType("application/json;charset=utf-8");
            return creatjwt;
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
        response.setContentType("application/json;charset=utf-8");
        return null;
    }

    @PostMapping("/admindoctorlogin")//TODO 医生登录
    @ResponseBody
    public Object admindoctorlogin(@RequestBody JSONObject json, HttpServletResponse response,HttpServletRequest request){
        String username = json.getString("username");
        String password = json.getString("password");
        System.out.println("username--医生--"+username);
        System.out.println("password--医生--"+password);
        Doctor doctorlogin = userService.doctorlogin(username, password);
        if(doctorlogin!=null)
        {
            request.getSession().setAttribute("doctor",doctorlogin);
            String creatjwt = JWTuuid.creatjwt(username, doctorlogin);
            System.out.println("jwt码----医生-----"+creatjwt);

            response.setContentType("application/json;charset=utf-8");
            return creatjwt;
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
        response.setContentType("application/json;charset=utf-8");
        return null;
    }




    @PostMapping("/register")//TODO 用户注册
    @ResponseBody
    public Map<String, Object> register(@RequestBody JSONObject json, HttpServletResponse response, HttpServletRequest req){
        Map<String, Object> resultMap=new HashMap<>();
        String username = json.getString("username");
        String password = json.getString("password");
        String name=json.getString("name");
        String sex=json.getString("sex");
        String age=json.getString("age");
        Date date=new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user=new User(0,username,password, formatter.format(date),name,sex,Integer.parseInt(age));
        int insert = userService.insert(user);
        if(insert!=0){

            resultMap.put("message","注册成功");
            return resultMap;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
        response.setContentType("application/json;charset=utf-8");
        return null;
    }

    @GetMapping("/registerview")//TODO 进入注册页面
    public String registerview(){
        return "registerview";
    }

    @GetMapping("/main")// TODO 跳转主页
    public String main()
    {
        return "main";
    }


    @GetMapping("/adminmain")// TODO 跳转管理员主页
    public String adminmain()
    {
        return "adminmain";
    }

    @GetMapping("/admindoctormain")// TODO 跳转医生主页
    public String doctornmain()
    {
        return "doctor/doctormain";
    }


    @GetMapping("/loginview")// TODO 跳转管理员登录界面
    public String adminloginview()
    {
        return "adminloginview";
    }

    @GetMapping("/doctorloginview")// TODO 跳转医生登录页面
    public String doctorloginview(){
        return "doctor/doctorloginview";
    }

    @GetMapping("/quit")// TODO 用户退出
    public String quit(HttpServletRequest request, HttpServletResponse response){
        return "index";
    }


    @GetMapping("/adminquit")// TODO 管理员返回主页
    public String adminquit(HttpServletRequest request, HttpServletResponse response)
    {
        // TODO: 使用 admintoken 进行管理员身份验证
        return "main";
    }

}
