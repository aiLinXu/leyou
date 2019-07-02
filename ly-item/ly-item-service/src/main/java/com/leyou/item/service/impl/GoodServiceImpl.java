package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.BeanHelper;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.SkuMapper;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.service.BrandService;
import com.leyou.item.service.CategoryService;
import com.leyou.item.service.GoodService;
import com.leyou.pojo.dto.BrandDTO;
import com.leyou.pojo.dto.CategoryDTO;
import com.leyou.pojo.dto.SpuDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xualin
 * @version v1.0
 * @date 2019/4/2 11:45
 * @description
 **/

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public PageResult<SpuDTO> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable) {
        //1.分页
        PageHelper.startPage(page, rows);

        //2. 过滤条件
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        //2.1 搜索过滤条件
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%");
        }

        // 2.2 上下架过滤
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }

        //2.3 安装默认事件排序
        example.setOrderByClause("update_time DESC");


        //3. 查询结果
        List<Spu> list = spuMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.GOODS_NOT_FOUND);
        }

        //4. 封装分页结果
        PageInfo<Spu> pageInfo = new PageInfo<>(list);

        //  DTO转换
        List<SpuDTO> spuDtoList = BeanHelper.copyWithCollection(list, SpuDTO.class);

        //定义一个方法从spudto中根据cid和bid查询分类和品牌
        queryCategoryAndBrandById(spuDtoList);

        //5 解析数据
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(), spuDtoList);


    }


    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    private void queryCategoryAndBrandById(List<SpuDTO> spuDtoList) {
        //遍历集合
        for (SpuDTO spuDTO : spuDtoList) {
            //获取品牌ID
            Long brandId = spuDTO.getBrandId();
            BrandDTO brand = brandService.queryBrandById(brandId);
            //把通过品牌Id查到的品牌的名字存入spuDTO中返回前端
            spuDTO.setBrandName(brand.getName());

            //获取分类ID
            List<Long> categoryIds = spuDTO.getCategoryIds();
            //List<CategoryDTO> list = categoryService.queryCategoryByCid(categoryIds);
            //流式思想
            String name =
                    categoryService.queryCategoryByCid(categoryIds)
                            .stream()
                            .map(CategoryDTO::getName).collect(Collectors.joining("/"));
            spuDTO.setCategoryName(name);

        }
    }


    /**
     * 商品规格参数
     *
     * @param spuDTO
     */

    @Override
    public void saveGoods(SpuDTO spuDTO) {
        //新增spu
        Spu spu = BeanHelper.copyProperties(spuDTO, Spu.class);
        spu.setSaleable(false);
        int count = spuMapper.insertSelective(spu);
        if (count != 1) {
            throw new LyException(ExceptionEnum.INSERT_OPERATION_FAIL);
        }


        //新增spudetail
        SpuDetail spuDetail = BeanHelper.copyProperties(spuDTO.getSpuDetailDTO(), SpuDetail.class);
        spuDetail.setSpuId(spuDTO.getId());
        count = spuDetailMapper.insertSelective(spuDetail);
        if (count != 1) {
            throw new LyException(ExceptionEnum.INSERT_OPERATION_FAIL);
        }


        //新增sku
        List<Sku> skuList = BeanHelper.copyWithCollection(spuDTO.getSkus(), Sku.class);
        for (Sku sku : skuList) {
            sku.setSpuId(spu.getId());
            sku.setEnable(false);
        }
        count = skuMapper.insertList(skuList);
        if (count != skuList.size()) {
            throw new LyException(ExceptionEnum.INSERT_OPERATION_FAIL);
        }
    }
}



