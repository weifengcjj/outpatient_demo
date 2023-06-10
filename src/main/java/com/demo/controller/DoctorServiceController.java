package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.Model.*;
import com.demo.Service.AdminService;
import com.demo.Service.DemoService;
import com.demo.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DoctorServiceController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/admintypeOrder")//获取相关科室的预约单
    public String typeOrder(Model model, HttpServletRequest request){
        Doctor doctor = (Doctor) request.getSession().getAttribute("doctor");
        List<Order> orders = doctorService.typeOrder(doctor.getType().getId());
        List<Drug> drugAll = adminService.drugAll();

        model.addAttribute("drugAll",drugAll);
        model.addAttribute("typeOrder",orders);
        return "doctor/typeOrder";
    }

    @GetMapping("/admindoctorprescription")//TODO 获取科室相关处方单
    public String prescriptionone(Model model, HttpServletRequest request){
        Doctor doctor = (Doctor) request.getSession().getAttribute("doctor");
        List<Prescription_drug> typeper = doctorService.typeper(doctor.getType().getId());//根据医生科室
        model.addAttribute("typepre",typeper);
        return "doctor/prescriptiondoctor";
    }

    @PostMapping("/adminprescription")// TODO 处理处方单
    @ResponseBody
    public Map<String, Object> prescription(@RequestBody JSONObject json, HttpServletResponse response, HttpServletRequest req){
        Map<String, Object> resultMap = new HashMap<>();
        String flag=json.getString("flag");
        int flagg=Integer.parseInt(flag);
        if(flagg==2){
            resultMap.put("message","该预约已被处理---");
            response.setContentType("application/json;charset=utf-8");
            return resultMap;
        }
        String oid = json.getString("oid");
        String drugid=json.getString("drugid");
        String drugname=json.getString("name");
        String goods=json.getString("goods");
        System.out.println("医生建议"+goods);

        int oidd=Integer.parseInt(oid);

        Order order=new Order();
        order.setId(oidd);//订单

        Drug drug=new Drug();
        drug.setId(Integer.parseInt(drugid));//药品

        Doctor doctor = (Doctor) req.getSession().getAttribute("doctor");//医生

        int createprescription = doctorService.createprescription(new Prescription_drug(order, doctor, drug, goods));
        if(createprescription!=0){
            int i = doctorService.UpdateFlag(oidd);
            if(i!=0){
                resultMap.put("message","处理完成---");
                response.setContentType("application/json;charset=utf-8");
                return resultMap;
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
        response.setContentType("application/json;charset=utf-8");
        return null;
    }




}
