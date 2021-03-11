package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.system.domain.ChinaAreaTable;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 地区Service接口
 * 
 * @author puzzle
 * @date 2021-01-12
 */
public interface IChinaAreaTableService 
{
    /**
     * 查询地区
     * 
     * @param areaId 地区ID
     * @return 地区
     */
    public ChinaAreaTable selectChinaAreaTableById(Long areaId);


    public int selectareaCount(long areaid);

    /**
     * 查询地区列表
     * 
     * @param chinaAreaTable 地区
     * @return 地区集合
     */
    public List<ChinaAreaTable> selectChinaAreaTableList(ChinaAreaTable chinaAreaTable);

    /**
     * 新增地区
     * 
     * @param chinaAreaTable 地区
     * @return 结果
     */
    public int insertChinaAreaTable(ChinaAreaTable chinaAreaTable);

    /**
     * 修改地区
     * 
     * @param chinaAreaTable 地区
     * @return 结果
     */
    public int updateChinaAreaTable(ChinaAreaTable chinaAreaTable);


    public ChinaAreaTable selectareaById(Long areaId);


    /**
     * 批量删除地区
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChinaAreaTableByIds(String ids);

    /**
     * 删除地区信息
     * 
     * @param areaId 地区ID
     * @return 结果
     */
    public int deleteChinaAreaTableById(Long areaId);

    /**
     * 查询地区树列表
     * 
     * @return 所有地区信息
     */
    public List<Ztree> selectChinaAreaTableTree();
}
