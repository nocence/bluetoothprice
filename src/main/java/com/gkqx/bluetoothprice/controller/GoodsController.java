package com.gkqx.bluetoothprice.controller;

import com.gkqx.bluetoothprice.common.resultCommon.ResultCommon;
import com.gkqx.bluetoothprice.dto.Result;
import com.gkqx.bluetoothprice.model.Goods;
import com.gkqx.bluetoothprice.service.GoodsService;
import com.gkqx.bluetoothprice.util.fileUtil.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName GoodsController
 * @Description 商品信息处理类
 * @Author Innocence
 * @Date 2019/5/7 000714:53
 * @Version 1.0
 **/
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("insertGoods")
    @ResponseBody
    public Result insertGoodsInfo(Goods goods){
        Result res = new Result();
        System.out.println("添加商品信息的请求到达");
        FileUtil fileUtil = new FileUtil();
        String goodsId = fileUtil.creatFileName();
        goods.setGoodsId(goodsId);
        Integer goodId = goodsService.addGoodsInfo(goods);
        if(goodId!=null){
            res.setCode(ResultCommon.SUCCESS_CODE);
        }else{
            res.setCode(ResultCommon.FAILED_CODE);
        }
        return res;
    }

    @RequestMapping("getAllGoods")
    @ResponseBody
    public Result getAllGoods(Goods goods){
        Result res = new Result();
        System.out.println("查询所有商品请求到达");
        List<Goods> allGoods = goodsService.getAllGoods(goods);
        res.setCode(ResultCommon.SUCCESS_CODE);
        res.setList(allGoods);
        return res;
    }
}
