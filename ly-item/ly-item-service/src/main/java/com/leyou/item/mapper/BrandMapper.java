package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/

@Repository
public interface BrandMapper extends Mapper<Brand> {

    /**
     * 通过品牌id查询对应品牌商品
     * @param id
     * @param ids
     * @return
     */
    int saveCategoryBrand(@Param("bid") Long id, @Param("ids") List<Long> ids);

    /**
     * 通过分类Id查询同类品牌商品集合
     * @param cid
     * @return
     */
    @Select("SELECT b.id, b.name, b.letter, b.image FROM tb_category_brand cb INNER JOIN tb_brand b ON b.id = cb.brand_id WHERE cb.category_id=#{cid} ")
    List<Brand> queryBrandByCid(@Param("cid") Long cid);

}