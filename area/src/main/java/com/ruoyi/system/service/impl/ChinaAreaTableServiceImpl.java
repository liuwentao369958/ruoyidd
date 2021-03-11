package com.ruoyi.system.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ChinaAreaTableMapper;
import com.ruoyi.system.domain.ChinaAreaTable;
import com.ruoyi.system.service.IChinaAreaTableService;
import com.ruoyi.common.core.text.Convert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

/**
 * 地区Service业务层处理
 * 
 * @author puzzle
 * @date 2021-01-12
 */
@Service
public class ChinaAreaTableServiceImpl implements IChinaAreaTableService 
{
    @Autowired
    private ChinaAreaTableMapper chinaAreaTableMapper;

    /**
     * 查询地区
     * 
     * @param areaId 地区ID
     * @return 地区
     */
    @Override
    public ChinaAreaTable selectChinaAreaTableById(Long areaId)
    {
        return chinaAreaTableMapper.selectChinaAreaTableById(areaId);
    }



    @Override
    public int selectareaCount(long areaid) {
        ChinaAreaTable chinaAreaTable = new ChinaAreaTable();
        chinaAreaTable.setAreaId(areaid);
        return chinaAreaTableMapper.selectareaCount(chinaAreaTable);
    }

    /**
     * 查询地区列表
     * 
     * @param chinaAreaTable 地区
     * @return 地区
     */
    @Override
    public List<ChinaAreaTable> selectChinaAreaTableList(ChinaAreaTable chinaAreaTable)
    {
        return chinaAreaTableMapper.selectChinaAreaTableList(chinaAreaTable);
    }

    /**
     * 新增地区
     * 
     * @param chinaAreaTable 地区
     * @return 结果
     */
    @Override
    public int insertChinaAreaTable(ChinaAreaTable chinaAreaTable)
    {
        ChinaAreaTable info = chinaAreaTableMapper.selectareaById(chinaAreaTable.getParentareaId());
        if(info==null){
            chinaAreaTable.setCreateTime(DateUtils.getNowDate());
            return chinaAreaTableMapper.insertChinaAreaTable(chinaAreaTable);
        }
        chinaAreaTable.setCreateTime(DateUtils.getNowDate());
        chinaAreaTable.setAncestors(info.getAncestors() + "," + chinaAreaTable.getParentareaId());
        return chinaAreaTableMapper.insertChinaAreaTable(chinaAreaTable);
    }

    /**
     * 修改地区
     * 
     * @param chinaAreaTable 地区
     * @return 结果
     */
    @Override
    public int updateChinaAreaTable(ChinaAreaTable chinaAreaTable)
    {
        chinaAreaTable.setUpdateTime(DateUtils.getNowDate());
        return chinaAreaTableMapper.updateChinaAreaTable(chinaAreaTable);
    }

    @Override
    public ChinaAreaTable selectareaById(Long areaId) {
        return chinaAreaTableMapper.selectareaById(areaId);
    }

    /**
     * 删除地区对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChinaAreaTableByIds(String ids)
    {
        return chinaAreaTableMapper.deleteChinaAreaTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除地区信息
     * 
     * @param areaId 地区ID
     * @return 结果
     */
    @Override
    public int deleteChinaAreaTableById(Long areaId)
    {
        return chinaAreaTableMapper.deleteChinaAreaTableById(areaId);
    }

    /**
     * 查询地区树列表
     * 
     * @return 所有地区信息
     */
    @Override
    public List<Ztree> selectChinaAreaTableTree()
    {
        List<ChinaAreaTable> chinaAreaTableList = chinaAreaTableMapper.selectChinaAreaTableList(new ChinaAreaTable());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (ChinaAreaTable chinaAreaTable : chinaAreaTableList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(chinaAreaTable.getAreaId());
            ztree.setpId(chinaAreaTable.getParentareaId());
            ztree.setName(chinaAreaTable.getAreaName());
            ztree.setTitle(chinaAreaTable.getAreaName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
