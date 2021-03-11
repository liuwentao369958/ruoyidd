package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Putindian;

/**
 * 投放点Service接口
 * 
 * @author lwt
 * @date 2021-01-22
 */
public interface IPutindianService 
{
    /**
     * 查询投放点
     * 
     * @param putinId 投放点ID
     * @return 投放点
     */
    public Putindian selectPutindianById(Long putinId);

    /**
     * 查询投放点列表
     * 
     * @param putindian 投放点
     * @return 投放点集合
     */
    public List<Putindian> selectPutindianList(Putindian putindian);

    public List<Putindian> toufangdianchazhao(Long plotid);

    /**
     * 新增投放点
     * 
     * @param putindian 投放点
     * @return 结果
     */
    public int insertPutindian(Putindian putindian);

    /**
     * 修改投放点
     * 
     * @param putindian 投放点
     * @return 结果
     */
    public int updatePutindian(Putindian putindian);

    /**
     * 批量删除投放点
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePutindianByIds(String ids);

    /**
     * 删除投放点信息
     * 
     * @param putinId 投放点ID
     * @return 结果
     */
    public int deletePutindianById(Long putinId);
}
