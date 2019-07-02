package com.leyou.item.service;

import com.leyou.pojo.dto.SpecGroupDTO;
import com.leyou.pojo.dto.SpecParamDTO;

import java.util.List;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/

public interface SpecService {
    /**
     * 根据分类id查询规格组
     * @param cid
     * @return
     */
    List<SpecGroupDTO> querySpecGroupByCid(Long cid);


    /**
     * 根据规格参数查询规格参数表中数据
     * @param gid
     * @param cid
     * @param searching
     * @return
     */
    List<SpecParamDTO> querySpecParam(Long gid, Long cid, Boolean searching);
}
