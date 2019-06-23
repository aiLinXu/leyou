package com.leyou.item.web;

import com.leyou.item.service.ItemService;
import com.leyou.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> saveItem(Item item){

            Item item1 = itemService.saveItem(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(item1);


    }
}
