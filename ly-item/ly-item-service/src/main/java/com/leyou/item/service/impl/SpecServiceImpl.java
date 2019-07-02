package com.leyou.item.service.impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.BeanHelper;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecService;
import com.leyou.pojo.dto.SpecGroupDTO;
import com.leyou.pojo.dto.SpecParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/

@Service
public class SpecServiceImpl implements SpecService {
    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    /**
     * 根据分类ID查询规格组
     *
     * @param cid
     * @return 规格组集合
     */
    @Override
    public List<SpecGroupDTO> querySpecGroupByCid(Long cid) {

        //根据分类ID查询规格组
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> list = specGroupMapper.select(specGroup);

        //健壮性判断  判断查询到的集合是否为空
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.SPEC_NOT_FOUND);
        }

        return BeanHelper.copyWithCollection(list, SpecGroupDTO.class);


    }


    /**
     * 查询规格参数表，得到需要的字段数据
     *
     * @param gid       根据规格组id查询规格参数表
     * @param cid       根据分类id查询规格参数表
     * @param searching 根据搜索字段查询规格参数表
     * @return
     */
    @Override
    public List<SpecParamDTO> querySpecParam(Long gid, Long cid, Boolean searching) {
        if (gid==null && cid==null && searching==null) {
            throw new LyException(ExceptionEnum.INVALID_NOTIFY_PARAM);
        }

        //查询条件
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);

        //查询
        List<SpecParam> list = specParamMapper.select(specParam);
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.INVALID_PARAM_ERROR);
        }

        return BeanHelper.copyWithCollection(list, SpecParamDTO.class);
    }
}

