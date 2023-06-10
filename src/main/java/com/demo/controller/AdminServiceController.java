package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.Model.Doctor;
import com.demo.Model.Drug;
import com.demo.Model.Order;
import com.demo.Model.Prescription_drug;
import com.demo.Service.AdminService;
import com.demo.Service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class AdminServiceController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private DemoService demoService;

    @GetMapping("/adminorderAll")// TODO 管理员获取所有订单
    public String orderAll(Model model, HttpServletRequest request){
        List<Order> orders = adminService.orderAll();
        model.addAttribute("orderAll",orders);
        return "adminservice/orderAll";
    }

    @GetMapping("/admindoctorAll")// TODO 管理员获取所有医生
    public String doctorAll(Model model, HttpServletRequest request){
        List<Doctor> doctors = adminService.doctorAll();
        model.addAttribute("doctorAll",doctors);
        return "adminservice/doctorAll";
    }

    @GetMapping("/admindrugAll")// TODO 管理员获取所有药品
    public String drugAll(Model model, HttpServletRequest request){
        List<Drug> drugAll = adminService.drugAll();
        model.addAttribute("drugAll",drugAll);
        return "adminservice/drugAll";
    }

    @GetMapping("/adminprescription")//TODO 管理员获取所有处方单
    public String adminprescription(Model model, HttpServletRequest request){
        List<Prescription_drug> prescription_drugs = adminService.preAll();
        model.addAttribute("preall",prescription_drugs);
        return "adminservice/prescriptionAll";
    }
    @PostMapping("admininsertdrug")// TODO 管理员添加药品
    @ResponseBody
    public Map<String, Object> admininsertdrug(@RequestBody JSONObject json, HttpServletResponse response, HttpServletRequest req){
        Map<String, Object> resultMap = new HashMap<>();
        String goods1=json.getString("goods1");
        String goods2=json.getString("goods2");
        String goods3=json.getString("goods3");//价格
        String goods4=json.getString("goods4");//库存
        if(goods1.equals("")||goods2.equals("")||goods3.equals("")||goods4.equals("")){
            resultMap.put("message","请补全信息！！！");
            response.setContentType("application/json;charset=utf-8");
            return resultMap;
        }
        double price=Double.parseDouble(goods3);
        int stock=Integer.parseInt(goods4);
        Drug drug=new Drug(0,goods1,goods2,price,stock);
        int i = adminService.insertDrug(drug);
        if(i!=0){
            resultMap.put("message","添加药品成功");
            response.setContentType("application/json;charset=utf-8");
            return resultMap;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
        response.setContentType("application/json;charset=utf-8");
        return null;
    }

}
