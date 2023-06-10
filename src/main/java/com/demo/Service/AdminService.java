package com.demo.Service;

import com.demo.Dao.AdminServiceDao;
import com.demo.Model.Doctor;
import com.demo.Model.Drug;
import com.demo.Model.Order;
import com.demo.Model.Prescription_drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements AdminServiceDao {
    @Autowired
    private AdminServiceDao adminServiceDao;

    @Override
    public List<Order> orderAll() {
        return adminServiceDao.orderAll();
    }

    @Override
    public List<Doctor> doctorAll() {
        return adminServiceDao.doctorAll();
    }

    @Override
    public List<Drug> drugAll() {
        return adminServiceDao.drugAll();
    }

    @Override
    public List<Prescription_drug> preAll() {
        return adminServiceDao.preAll();
    }

    @Override
    public int insertDrug(Drug drug) {
        return adminServiceDao.insertDrug(drug);
    }

}
