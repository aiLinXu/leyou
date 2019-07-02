package com.leyou.item.service;

import com.leyou.pojo.dto.CategoryDTO;

import java.util.List;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/

public interface CategoryService {

    /**
     * 根据分类Id查询分类集合
     * @param pid
     * @return
     */
    List<CategoryDTO> queryByParentId(Long pid);


    /**
     * 根据分类ID查询分类
     * @param cids
     * @returns
     */
    List<CategoryDTO> queryCategoryByCid(List<Long> cids);
}
