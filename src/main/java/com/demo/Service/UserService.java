package com.demo.Service;

import com.demo.Dao.UserDao;
import com.demo.Model.Admin;
import com.demo.Model.Doctor;
import com.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDao {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(String username, String password) {
        return userDao.login(username,password);
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public Admin adminlogin(String username, String password) {
        return userDao.adminlogin(username,password);
    }

    @Override
    public Doctor doctorlogin(String username, String password) {
        return userDao.doctorlogin(username,password);
    }


}
