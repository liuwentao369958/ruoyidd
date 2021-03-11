package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Cabinet;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 货柜管理Service接口
 *
 * @author lwt
 * @date 2021-01-21
 */
public interface ICabinetService
{
    /**
     * 查询货柜管理
     *
     * @param cabinetId 货柜管理ID
     * @return 货柜管理
     */
    public Cabinet selectCabinetById(Long cabinetId);

    /**
     * 查询货柜管理列表
     *
     * @param cabinet 货柜管理
     * @return 货柜管理集合
     */
    public List<Cabinet> selectCabinetList(Cabinet cabinet);

    /**
     * 新增货柜管理
     *
     * @param cabinet 货柜管理
     * @return 结果
     */
    public int insertCabinet(Cabinet cabinet);

    /**
     * 修改货柜管理
     *
     * @param cabinet 货柜管理
     * @return 结果
     */
    public int updateCabinet(Cabinet cabinet);


    public Cabinet selectbianhao(long cabinetid);


    public int selectcabineTCount(long cabinetId);
    /**
     * 批量删除货柜管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCabinetByIds(String ids);

    /**
     * 删除货柜管理信息
     *
     * @param cabinetId 货柜管理ID
     * @return 结果
     */
    public int deleteCabinetById(Long cabinetId);

    /**
     * 查询货柜管理树列表
     *
     * @return 所有货柜管理信息
     */
    public List<Ztree> selectCabinetTree();
}
