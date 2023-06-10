package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.Model.*;
import com.demo.Service.DemoService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ServiceController {
    @Autowired
    private DemoService demoService;

    @GetMapping("/insertservice")// TODO 打开增加业务页面
    public String insertservice(Model model){
        List<Type> alltype = demoService.alltype();
        model.addAttribute("alltype",alltype);
        return "service/insertservice";
    }

    @GetMapping("/personalMessage")//TODO 个人信息
    public String personalMessage(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("拿到的用户"+user.getUsername());
        model.addAttribute("user",user);
        return "personalinfo/personalMessage";
    }


    @GetMapping("/admindoctorpersonalMessage")// TODO 医生个人信息
    public String doctorpersonalMessage(Model model, HttpServletRequest request){
        Doctor doctor= (Doctor) request.getSession().getAttribute("doctor");
        model.addAttribute("doctor",doctor);
        return "personalinfo/doctorpersonalMessage";
    }


    @PostMapping("/insertorder")// TODO 创建订单
    @ResponseBody
    public Map<String, Object> insertorder(@RequestBody JSONObject json, HttpServletResponse response, HttpServletRequest req){
        Map<String, Object> resultMap = new HashMap<>();
        String type = json.getString("type");
        String typeid = json.getString("typeid");
        String goods= json.getString("goods");
        if(goods.equals("")){
            resultMap.put("message","病情简述不能为空!!");
            return resultMap;
        }
        User user = (User) req.getSession().getAttribute("user");
        List<Order> selectorderone = demoService.selectorderone(user.getId());
        try{
            if(selectorderone.get(0).getFlag().getId()==1){
                resultMap.put("message","有未处理的预约订单，请你仔细查阅");
                response.setContentType("application/json;charset=utf-8");
                return resultMap;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Date date=new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderid=UUID.randomUUID().toString();

        Type type1 = new Type(Integer.parseInt(typeid), type);

        Order order=new Order(0,user,type1,goods,formatter.format(date),orderid,new Flag());//创建出当前订单

        int insertorder = demoService.insertorder(order);
        if(insertorder!=0)
        {
            resultMap.put("message","预约成功，等待医生检阅");
            return resultMap;
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
        response.setContentType("application/json;charset=utf-8");
        return null;
    }

    @GetMapping("/selectorderone")// TODO 获取当前用户的所有订单
    public String selectorderone(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("拿到的用户"+user.getUsername());
        List<Order> selectorderone = demoService.selectorderone(user.getId());
        model.addAttribute("orderlist",selectorderone);
        return "service/orderone";
    }

    @GetMapping("/prescriptionone")//TODO 获取当前用户所有处方单
    public String prescriptionone(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("拿到的用户"+user.getUsername());
        List<Prescription_drug> preone = demoService.preone(user.getId());
        model.addAttribute("preone",preone);
        return "service/prescriptionone";
    }






}
