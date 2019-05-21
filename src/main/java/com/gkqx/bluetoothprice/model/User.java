package com.gkqx.bluetoothprice.model;

/**
 * @ClassName User
 * @Description 用户实体类
 * @Author Innocence
 * @Date 2019/5/5 000517:15
 * @Version 1.0
 **/
public class User {
    private Integer userId;

    private String name;

    private String passWord;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
