package com.gkqx.bluetoothprice.mapper;


import com.gkqx.bluetoothprice.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
    * 根据用户名和密码查询用户
    * @author Innocence
    * @date 2019/5/5 000517:18
    * @param
    * @return
    */
    User queryOne(User user);
}
