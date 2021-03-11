package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Putindian;

/**
 * 投放点Mapper接口
 * 
 * @author lwt
 * @date 2021-01-22
 */
public interface PutindianMapper 
{
    /**
     * 查询投放点
     * 
     * @param putinId 投放点ID
     * @return 投放点
     */
    public Putindian selectPutindianById(Long putinId);

    public List<Putindian> toufangdianchazhao(Long plotid);


    /**
     * 查询投放点列表
     * 
     * @param putindian 投放点
     * @return 投放点集合
     */
    public List<Putindian> selectPutindianList(Putindian putindian);

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
     * 删除投放点
     * 
     * @param putinId 投放点ID
     * @return 结果
     */
    public int deletePutindianById(Long putinId);

    /**
     * 批量删除投放点
     * 
     * @param putinIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePutindianByIds(String[] putinIds);
}
