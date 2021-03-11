package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.CommunityManagement;

import java.util.List;

/**
 * 小区管理Mapper接口
 * 
 * @author LWT
 * @date 2021-01-13
 */
public interface CommunityManagementMapper 
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


    public int selectplotCount(CommunityManagement caina);

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
     * 删除小区管理
     * 
     * @param plotId 小区管理ID
     * @return 结果
     */
    public int deleteCommunityManagementById(Long plotId);

    /**
     * 批量删除小区管理
     * 
     * @param plotIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommunityManagementByIds(String[] plotIds);
}
