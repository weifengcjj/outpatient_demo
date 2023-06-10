package com.demo.Service;

import com.demo.Dao.DoctorServiceDao;
import com.demo.Model.Order;
import com.demo.Model.Prescription_drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService implements DoctorServiceDao {
    @Autowired
    private DoctorServiceDao doctorServiceDao;
    @Override
    public List<Order> typeOrder(int tid) {
        return doctorServiceDao.typeOrder(tid);
    }

    @Override
    public int createprescription(Prescription_drug prescription_drug) {
        return doctorServiceDao.createprescription(prescription_drug);
    }

    @Override
    public int UpdateFlag(int oid) {
        return doctorServiceDao.UpdateFlag(oid);
    }

    @Override
    public List<Prescription_drug> typeper(int tid) {
        return doctorServiceDao.typeper(tid);
    }
}
