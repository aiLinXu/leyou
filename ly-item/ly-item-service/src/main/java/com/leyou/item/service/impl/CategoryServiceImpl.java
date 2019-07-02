package com.leyou.item.service.impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.BeanHelper;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import com.leyou.pojo.dto.CategoryDTO;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> queryByParentId(Long pid) {
        //查询条件，通用mapper会把对象中的非空字段作为查询条件
        Category category = new Category();
        //非空字段
        category.setParentId(pid);
        List<Category> list = categoryMapper.select(category);

        //健壮性判断
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.CARTS_NOT_FOUND);
        }

        //将返回值类型借用工具类转换为我们需要的类型
        List<CategoryDTO> categoryDTOS = BeanHelper.copyWithCollection(list, CategoryDTO.class);
        return categoryDTOS;
    }

    /**
     * 根据分类id集合查询分类集合
     * @param cids
     * @return
     */
    @Override
    public List<CategoryDTO> queryCategoryByCid(List<Long> cids) {
        List<Category> list = categoryMapper.selectByIdList(cids);
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return BeanHelper.copyWithCollection(list,CategoryDTO.class);
    }
}
