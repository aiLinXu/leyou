package com.leyou.item.web;

import com.leyou.item.service.SpecService;
import com.leyou.pojo.dto.SpecGroupDTO;
import com.leyou.pojo.dto.SpecParamDTO;
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
@RequestMapping("spec")
public class SpecController {
    @Autowired
    private SpecService specService;

    /**
     * 根据分类ID查询规格组
     * @param cid
     * @return 规格组集合
     */
    @GetMapping("groups/of/category")
    public ResponseEntity<List<SpecGroupDTO>> querySpecGroupByCid(@RequestParam("id") Long cid){

       return ResponseEntity.ok(specService.querySpecGroupByCid(cid));
    }

    /**
     *根据规格组查询规格参数
     * @param gid 规格组gid
     * @return 规格组集合
     */
    @GetMapping("/params")
    public ResponseEntity<List<SpecParamDTO>> querySpecParams(
            @RequestParam(value = "gid",required = false) Long gid,
            @RequestParam(value = "cid",required = false) Long cid,
            @RequestParam(value = "searching",required = false)Boolean searching
    ){
        return ResponseEntity.ok(specService.querySpecParam(gid,cid,searching));
    }
}
