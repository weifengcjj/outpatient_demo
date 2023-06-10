package com.demo.Service;

import com.demo.Dao.ServiceDao;
import com.demo.Model.Order;
import com.demo.Model.Prescription_drug;
import com.demo.Model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DemoService implements ServiceDao {

    @Autowired
    private ServiceDao serviceDao;

    @Override
    public List<Type> alltype() {
        return serviceDao.alltype();
    }

    @Override
    public int insertorder(Order order) {
        return serviceDao.insertorder(order);
    }

    @Override
    public List<Order> selectorderone(int uid) {
        return serviceDao.selectorderone(uid);
    }

    @Override
    public List<Prescription_drug> preone(int uid) {
        return serviceDao.preone(uid);
    }
}
