package com.leyou.item.service;

import com.leyou.common.exception.LyException;
import com.leyou.pojo.Item;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/
@Service
public class ItemService {
    public Item saveItem(Item item){
        if (item.getPrice()==null) {
            throw new LyException(400,"价格不能为空");
        }
        int i = new Random().nextInt(100);
        item.setId(i);
        return item;
    }

}
