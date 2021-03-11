package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.system.domain.CommunityManagement;

import java.util.List;

/**
 * 小区管理Service接口
 * 
 * @author LWT
 * @date 2021-01-13
 */
public interface ICommunityManagementService 
{
    /**
     * 查询小区管理
     * 
     * @param plotId 小区管理ID
     * @return 小区管理
     */
    public CommunityManagement selectCommunityManagementById(Long plotId);

    /**
     * 查询小区管理列表
     * 
     * @param communityManagement 小区管理
     * @return 小区管理集合
     */
    public List<CommunityManagement> selectCommunityManagementList(CommunityManagement communityManagement);


    public int selectplotCount(long plotid);

    public CommunityManagement selectplotById(Long plotId);

    /**
     * 新增小区管理
     * 
     * @param communityManagement 小区管理
     * @return 结果
     */
    public int insertCommunityManagement(CommunityManagement communityManagement);

    /**
     * 修改小区管理
     * 
     * @param communityManagement 小区管理
     * @return 结果
     */
    public int updateCommunityManagement(CommunityManagement communityManagement);

    /**
     * 批量删除小区管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommunityManagementByIds(String ids);


    /**
     * 删除小区管理信息
     * 
     * @param plotId 小区管理ID
     * @return 结果
     */
    public int deleteCommunityManagementById(Long plotId);

    /**
     * 查询小区管理树列表
     * 
     * @return 所有小区管理信息
     */
    public List<Ztree> selectCommunityManagementTree();
}
