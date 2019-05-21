package com.gkqx.bluetoothprice.controller;

import com.gkqx.bluetoothprice.common.resultCommon.ResultCommon;
import com.gkqx.bluetoothprice.dto.Result;
import com.gkqx.bluetoothprice.model.FontTag;
import com.gkqx.bluetoothprice.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName TagsController
 * @Description 处理前台对价签的请求
 * @Author Innocence
 * @Date 2019/5/15 001511:52
 * @Version 1.0
 **/
@RestController
@RequestMapping("tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @ResponseBody
    @RequestMapping("showAllTags")
    public Result showTags(FontTag fontTag){
        Result res = new Result();
        List<FontTag> allTags = tagsService.getAllTagsToFont(fontTag);
        if (!allTags.isEmpty()){
            for (FontTag i:allTags) {
                if(i.getType()==1){
                    i.setState("使用中");
                }else if(i.getType()==0){
                    i.setState("未使用");
                }
            }
            res.setCode(ResultCommon.SUCCESS_CODE);
            res.setList(allTags);
        }else{
            res.setCode(ResultCommon.FAILED_CODE);
        }
        return res;
    }
}
