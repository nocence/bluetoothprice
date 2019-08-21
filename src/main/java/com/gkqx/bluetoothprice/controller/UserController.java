package com.gkqx.bluetoothprice.controller;

import com.gkqx.bluetoothprice.common.resultCommon.ResultCommon;
import com.gkqx.bluetoothprice.dto.Result;
import com.gkqx.bluetoothprice.model.User;
import com.gkqx.bluetoothprice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @ClassName UserController
 * @Description 用户请求处理
 * @Author Innocence
 * @Date 2019/5/5 000517:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public Result userLogin(User user, HttpSession session){
        System.out.println("登陆请求进来");
        Result res = new Result();
        if (user.getName()==null || user.getPassWord()==null) res.setCode(ResultCommon.FAILED_CODE);
        if (user.getName()!=null && user.getPassWord()!=null){
            User queryOne = userService.queryOne(user);
            if (queryOne!=null){
                if (queryOne.getName().equals(user.getName()) && queryOne.getPassWord().equals(user.getPassWord())){
                    session.setAttribute("loginUser",queryOne);
                    res.setCode(ResultCommon.SUCCESS_CODE);
                    res.setObj(queryOne);
                }else{
                    res.setCode(ResultCommon.FAILED_CODE);
                }
            }else{
                res.setCode(ResultCommon.FAILED_CODE);
            }
        }
        return res;
    }
}
