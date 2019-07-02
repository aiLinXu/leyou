package com.leyou.item.web;

import com.leyou.item.service.impl.GoodServiceImpl;
import com.leyou.pojo.dto.SpuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/

@RestController
public class GoodController {

    @Autowired
    private GoodServiceImpl goodService;


    @GetMapping("/spu/page")
    public ResponseEntity<Object> queryBrandByPid(
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "saleable", required = false)boolean saleable

    ){

        return ResponseEntity.ok(goodService.querySpuByPage(page,rows,key,saleable));
    }


    /**
     *
     * @param spuDTO
     * @return
     * @RequestBody 用于读取前端传来的数据，并将json数据转化为java对象，
     * 绑定到Controller的方法参数上
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveSave(@RequestBody SpuDTO spuDTO){
        goodService.saveGoods(spuDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
