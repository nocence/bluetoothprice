package com.gkqx.bluetoothprice.controller;

import com.gkqx.bluetoothprice.common.resultCommon.ResultCommon;
import com.gkqx.bluetoothprice.dto.Result;
import com.gkqx.bluetoothprice.model.Store;
import com.gkqx.bluetoothprice.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName StoreController
 * @Description 门店信息的处理类
 * @Author Innocence
 * @Date 2019/5/6 000618:52
 * @Version 1.0
 **/
@RestController
@RequestMapping("store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    /**
    * 添加门店
    * @author Innocence
    * @date 2019/5/7 000710:41
    * @param store
    * @return com.gkqx.bluetoothprice.dto.Result
    */
    @RequestMapping("addinfo")
    @ResponseBody
    public Result addInfo(Store store){
        System.out.println("添加门店信息的请求到达");
        Result res = new Result();
        if (store!=null){
            Integer storeid = storeService.addStore(store);
            if (storeid!=null){
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
    * 查询门店列表
    * @author Innocence
    * @date 2019/5/7 000710:41
    * @param
    * @return
    */
    @RequestMapping("showStores")
    @ResponseBody
    public Result getAllStores(Store store){
        System.out.println("查询所有门店的请求进来");
        Result res = new Result();
        List<Store> allStores = storeService.getAllStores(store);
        res.setCode(ResultCommon.SUCCESS_CODE);
        res.setList(allStores);
        return res;
    }
}
