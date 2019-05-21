package com.gkqx.bluetoothprice.serviceImpl;


import com.gkqx.bluetoothprice.mapper.UserMapper;
import com.gkqx.bluetoothprice.model.User;
import com.gkqx.bluetoothprice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryOne(User user) {
        return userMapper.queryOne(user);
    }
}
