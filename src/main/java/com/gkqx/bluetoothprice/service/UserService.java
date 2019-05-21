package com.gkqx.bluetoothprice.service;


import com.gkqx.bluetoothprice.model.User;

public interface UserService {
    //根据用户名和密码查询用户
    User queryOne(User user);
}
