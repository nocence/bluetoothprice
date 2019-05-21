package com.gkqx.bluetoothprice.controller;

import com.gkqx.bluetoothprice.common.resultCommon.ResultCommon;
import com.gkqx.bluetoothprice.dto.Result;
import com.gkqx.bluetoothprice.model.Store;
import com.gkqx.bluetoothprice.model.StoreWifis;
import com.gkqx.bluetoothprice.model.Wifis;
import com.gkqx.bluetoothprice.service.StoreService;
import com.gkqx.bluetoothprice.service.WifisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName WifisController
 * @Description TODD
 * @Author Innocence
 * @Date 2019/5/9 000917:46
 * @Version 1.0
 **/
@RestController
@RequestMapping("wifis")
public class WifisController {

    @Autowired
    private WifisService wifisService;

    @Autowired
    private StoreService storeService;

    @RequestMapping("addwifi")
    @ResponseBody
    public Result addWifi(Wifis wifis){
        System.out.println("添加wifi的请求到达");
        Result res = new Result();
        if (wifis.getWifiIp()!=null && wifis.getWifiIp()!="" && wifis.getStoreId()!=null ){
            //暂时默认添加的wifi都是工作状态，设置为1
            wifis.setType(1);
            Integer wifiId = wifisService.addWifi(wifis);
            if(wifiId!=null){
                res.setCode(ResultCommon.SUCCESS_CODE);
            }else{
                res.setCode(ResultCommon.FAILED_CODE);
            }
        }else{
            res.setCode(ResultCommon.FAILED_CODE);
        }
        return res;
    }

    /**
    * 添加wifi时的下拉查询所有门店的请求
    * @author Innocence
    * @date 2019/5/9 000917:55
    * @param store
    * @return com.gkqx.bluetoothprice.dto.Result
    */
    @RequestMapping("getAllStores")
    @ResponseBody
    public Result getAllStores(Store store){
        System.out.println("添加wifi的下拉框查询所有门店的请求到达");
        Result res = new Result();
        List<Store> allStores = storeService.getAllStores(store);
        if(!allStores.isEmpty()){
            res.setList(allStores);
            res.setCode(ResultCommon.SUCCESS_CODE);
        }else{
            res.setCode(ResultCommon.FAILED_CODE);
        }
        return res;
    }

    @RequestMapping("showWifis")
    @ResponseBody
    public Result getAllWifis(StoreWifis storeWifis){
        System.out.println("查询所有wifi请求到达");
        Result res = new Result();
        List<StoreWifis> allWifis = wifisService.getAllWifis(storeWifis);
        if(!allWifis.isEmpty()){
            for (StoreWifis i: allWifis) {
                if(i.getType()==1){
                    i.setState("使用中");
                }else if(i.getType()==0){
                    i.setState("未使用");
                }
            }
            res.setList(allWifis);
            res.setCode(ResultCommon.SUCCESS_CODE);
        }else{
            res.setCode(ResultCommon.FAILED_CODE);
        }
        return res;
    }
}
