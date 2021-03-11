package com.ruoyi.category.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.category.mapper.GoodsCategoryMapper;
import com.ruoyi.category.domain.GoodsCategory;
import com.ruoyi.category.service.IGoodsCategoryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 商品分类Service业务层处理
 * 
 * @author LWT
 * @date 2021-01-13
 */
@Service
public class GoodsCategoryServiceImpl implements IGoodsCategoryService 
{
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    /**
     * 查询商品分类
     * 
     * @param projectId 商品分类ID
     * @return 商品分类
     */
    @Override
    public GoodsCategory selectGoodsCategoryById(Long projectId)
    {
        return goodsCategoryMapper.selectGoodsCategoryById(projectId);
    }

    /**
     * 查询商品分类列表
     * 
     * @param goodsCategory 商品分类
     * @return 商品分类
     */
    @Override
    public List<GoodsCategory> selectGoodsCategoryList(GoodsCategory goodsCategory)
    {
        return goodsCategoryMapper.selectGoodsCategoryList(goodsCategory);
    }

    @Override
    public int selectcategoryCount(long projectId) {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setProjectId(projectId);
        return goodsCategoryMapper.selectcategoryCount(goodsCategory);
    }

    @Override
    public GoodsCategory selectcategoryById(long projectId) {
        return goodsCategoryMapper.selectcategoryById(projectId);
    }

    /**
     * 新增商品分类
     * 
     * @param goodsCategory 商品分类
     * @return 结果
     */
    @Override
    public int insertGoodsCategory(GoodsCategory goodsCategory)
    {
        GoodsCategory info = goodsCategoryMapper.selectcategoryById(goodsCategory.getParentprojectId());
        if(info==null){
            goodsCategory.setCreateTime(DateUtils.getNowDate());
            return goodsCategoryMapper.insertGoodsCategory(goodsCategory);
        }
        goodsCategory.setCreateTime(DateUtils.getNowDate());
        goodsCategory.setAncestors(info.getAncestors() + "," + goodsCategory.getParentprojectId());
        return goodsCategoryMapper.insertGoodsCategory(goodsCategory);
    }

    /**
     * 修改商品分类
     * 
     * @param goodsCategory 商品分类
     * @return 结果
     */
    @Override
    public int updateGoodsCategory(GoodsCategory goodsCategory)
    {
        goodsCategory.setUpdateTime(DateUtils.getNowDate());
        return goodsCategoryMapper.updateGoodsCategory(goodsCategory);
    }

    /**
     * 删除商品分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGoodsCategoryByIds(String ids)
    {
        return goodsCategoryMapper.deleteGoodsCategoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品分类信息
     * 
     * @param projectId 商品分类ID
     * @return 结果
     */
    @Override
    public int deleteGoodsCategoryById(Long projectId)
    {
        return goodsCategoryMapper.deleteGoodsCategoryById(projectId);
    }

    /**
     * 查询商品分类树列表
     * 
     * @return 所有商品分类信息
     */
    @Override
    public List<Ztree> selectGoodsCategoryTree()
    {
        List<GoodsCategory> goodsCategoryList = goodsCategoryMapper.selectGoodsCategoryList(new GoodsCategory());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (GoodsCategory goodsCategory : goodsCategoryList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(goodsCategory.getProjectId());
            ztree.setpId(goodsCategory.getParentprojectId());
            ztree.setName(goodsCategory.getProjectName());
            ztree.setTitle(goodsCategory.getProjectName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
