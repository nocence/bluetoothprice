package com.gkqx.bluetoothprice.util.connectionUtil;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * @ClassName DuplicateRemoveUtil
 * @Description 利用LinkedHashSet集合给list去重方法
 * @Author Innocence
 * @Date 2019/7/17 001715:23
 * @Version 1.0
 **/
public class DuplicateRemoveUtil {

    public void getSingle(List<String> list){
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
    }
}
