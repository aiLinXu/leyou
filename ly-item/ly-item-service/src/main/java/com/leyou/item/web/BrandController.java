package com.leyou.item.web;

import com.leyou.item.service.BrandService;
import com.leyou.pojo.dto.BrandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 分页查询品牌
     * @param page    当前页
     * @param rows    每页大小
     * @param key     收缩字段
     * @param sortBy  排序字段
     * @param desc    是否降序
     * @return
     */

    @GetMapping("page")
    public ResponseEntity<Object> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "sortBy", required = false)String sortBy,
            @RequestParam(value = "desc", defaultValue = "false")Boolean desc

    ){

       return ResponseEntity.ok(brandService.queryBrandByPage(page,rows,key,sortBy,desc));
    }


    /**
     * 新增品牌
     * @param brand
     * @param ids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(BrandDTO brand, @RequestParam("cids") List<Long> ids) {
        brandService.saveBrand(brand, ids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("of/category")
    public ResponseEntity<List<BrandDTO>> queryBrandByCid(@RequestParam("id") Long cid){
        return ResponseEntity.ok(brandService.queryBrandByCid(cid));

    }

}
