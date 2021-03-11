package com.ruoyi.category.mapper;

import java.util.List;
import com.ruoyi.category.domain.GoodsCategory;

/**
 * 商品分类Mapper接口
 * 
 * @author LWT
 * @date 2021-01-13
 */
public interface GoodsCategoryMapper 
{
    /**
     * 查询商品分类
     * 
     * @param projectId 商品分类ID
     * @return 商品分类
     */
    public GoodsCategory selectGoodsCategoryById(Long projectId);

    /**
     * 查询商品分类列表
     * 
     * @param goodsCategory 商品分类
     * @return 商品分类集合
     */
    public List<GoodsCategory> selectGoodsCategoryList(GoodsCategory goodsCategory);

    //--------------------------------------------------------------------

    public int selectcategoryCount(GoodsCategory caina);

    public GoodsCategory selectcategoryById(Long projectId);


    //--------------------------------------------------------------------
    /**
     * 新增商品分类
     * 
     * @param goodsCategory 商品分类
     * @return 结果
     */
    public int insertGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 修改商品分类
     * 
     * @param goodsCategory 商品分类
     * @return 结果
     */
    public int updateGoodsCategory(GoodsCategory goodsCategory);

    /**
     * 删除商品分类
     * 
     * @param projectId 商品分类ID
     * @return 结果
     */
    public int deleteGoodsCategoryById(Long projectId);

    /**
     * 批量删除商品分类
     * 
     * @param projectIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsCategoryByIds(String[] projectIds);
}
