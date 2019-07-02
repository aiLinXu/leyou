package com.leyou.item.service;

import com.leyou.common.vo.PageResult;
import com.leyou.pojo.dto.SpuDTO;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/

public interface GoodService {

    PageResult<SpuDTO> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable);

    void saveGoods(SpuDTO spuDTO);
}
