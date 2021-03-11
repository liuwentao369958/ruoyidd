package com.ruoyi.system.mapper;


import java.util.List;
import com.ruoyi.system.domain.Cabinet;
import com.ruoyi.system.domain.Putindian;
import org.apache.ibatis.annotations.Select;

/**
 * 货柜管理Mapper接口
 *
 * @author lwt
 * @date 2021-01-22
 */
public interface CabinetMapper
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


    public int selectcabineTCount(Cabinet caina);


    public Cabinet selectbianhao(long cabinetid);
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

    /**
     * 删除货柜管理
     *
     * @param cabinetId 货柜管理ID
     * @return 结果
     */
    public int deleteCabinetById(Long cabinetId);

    /**
     * 批量删除货柜管理
     *
     * @param cabinetIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCabinetByIds(String[] cabinetIds);

    /**
     * 批量删除投放点
     *
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePutindianByPutinIds(String[] cabinetIds);

    /**
     * 批量新增投放点
     *
     * @param putindianList 投放点列表
     * @return 结果
     */
    public int batchPutindian(List<Putindian> putindianList);


    /**
     * 通过货柜管理ID删除投放点信息
     *
     * @param roleId 角色ID
     * @return 结果
     */
    public int deletePutindianByPutinId(Long cabinetId);
}