package com.leyou.item.mapper;

import com.leyou.common.mapper.BaseMapper;
import com.leyou.item.pojo.Sku;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/

@Repository
public interface SkuMapper extends BaseMapper<Sku>, InsertListMapper<Sku> {
}
