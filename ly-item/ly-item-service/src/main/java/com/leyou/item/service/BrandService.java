package com.leyou.item.service;

import com.leyou.pojo.dto.BrandDTO;

import java.util.List;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/
public interface BrandService {
    /**
     * 品牌查询
     * @param page
     * @param rows
     * @param key
     * @param sortBy
     * @param desc
     * @return
     */
    Object queryBrandByPage(Integer page, Integer rows, String key, String sortBy, Boolean desc);

    /**
     * 新增品牌
     * @param brand
     * @param ids
     */
    void saveBrand(BrandDTO brand, List<Long> ids);

    /**
     * 根据品牌id查询品牌名称
     * @param bid
     * @return  返回值BrandDTO
     */
    BrandDTO queryBrandById(Long bid);



    /**
     * 通过分类id查询商品集合
     * @param cid
     * @return
     */
    List<BrandDTO> queryBrandByCid(Long cid);
}


