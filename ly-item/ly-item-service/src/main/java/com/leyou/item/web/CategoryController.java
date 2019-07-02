package com.leyou.item.web;

import com.leyou.item.service.CategoryService;
import com.leyou.pojo.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/
@RestController
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点查询商品类目
     * @param pid
     * @return
     */
    @GetMapping("/of/parent")
    public ResponseEntity<List<CategoryDTO>> queryByParentId(@RequestParam(value = "pid",defaultValue = "0") Long pid){
        List<CategoryDTO> list = categoryService.queryByParentId(pid);
        return ResponseEntity.ok(list);
    }

}
