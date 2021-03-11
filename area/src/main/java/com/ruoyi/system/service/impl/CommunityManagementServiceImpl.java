package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.CommunityManagement;
import com.ruoyi.system.mapper.CommunityManagementMapper;
import com.ruoyi.system.service.ICommunityManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 小区管理Service业务层处理
 * 
 * @author LWT
 * @date 2021-01-13
 */
@Service
public class CommunityManagementServiceImpl implements ICommunityManagementService 
{
    @Autowired
    private CommunityManagementMapper communityManagementMapper;

    /**
     * 查询小区管理
     * 
     * @param plotId 小区管理ID
     * @return 小区管理
     */
    @Override
    public CommunityManagement selectCommunityManagementById(Long plotId)
    {
        return communityManagementMapper.selectCommunityManagementById(plotId);
    }

    /**
     * 查询小区管理列表
     * 
     * @param communityManagement 小区管理
     * @return 小区管理
     */
    @Override
    public List<CommunityManagement> selectCommunityManagementList(CommunityManagement communityManagement)
    {
        return communityManagementMapper.selectCommunityManagementList(communityManagement);
    }

    /*验证是否有子目录*/
    @Override
    public int selectplotCount(long plotid) {
        CommunityManagement communityManagement = new CommunityManagement();
        communityManagement.setPlotId(plotid);
        return communityManagementMapper.selectplotCount(communityManagement);
    }

    @Override
    public CommunityManagement selectplotById(Long plotId) {
        return communityManagementMapper.selectplotById(plotId);
    }

    /**
     * 新增小区管理
     * 
     * @param communityManagement 小区管理
     * @return 结果
     */
    @Override
    public int insertCommunityManagement(CommunityManagement communityManagement)
    {
        CommunityManagement info = communityManagementMapper.selectplotById(communityManagement.getParentplotId());
        if(info==null){
            communityManagement.setCreateTime(DateUtils.getNowDate());
            return communityManagementMapper.insertCommunityManagement(communityManagement);
        }
        communityManagement.setCreateTime(DateUtils.getNowDate());
        communityManagement.setAncestors(info.getAncestors() + "," + communityManagement.getParentplotId());
        return communityManagementMapper.insertCommunityManagement(communityManagement);
    }

    /**
     * 修改小区管理
     * 
     * @param communityManagement 小区管理
     * @return 结果
     */
    @Override
    public int updateCommunityManagement(CommunityManagement communityManagement)
    {
        communityManagement.setUpdateTime(DateUtils.getNowDate());
        return communityManagementMapper.updateCommunityManagement(communityManagement);
    }

    /**
     * 删除小区管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCommunityManagementByIds(String ids)
    {
        return communityManagementMapper.deleteCommunityManagementByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除小区管理信息
     * 
     * @param plotId 小区管理ID
     * @return 结果
     */
    @Override
    public int deleteCommunityManagementById(Long plotId)
    {
        return communityManagementMapper.deleteCommunityManagementById(plotId);
    }

    /**
     * 查询小区管理树列表
     * 
     * @return 所有小区管理信息
     */
    @Override
    public List<Ztree> selectCommunityManagementTree()
    {
        List<CommunityManagement> communityManagementList = communityManagementMapper.selectCommunityManagementList(new CommunityManagement());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (CommunityManagement communityManagement : communityManagementList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(communityManagement.getPlotId());
            ztree.setpId(communityManagement.getParentplotId());
            ztree.setName(communityManagement.getPlotName());
            ztree.setTitle(communityManagement.getPlotName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
