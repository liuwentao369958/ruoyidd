package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PutindianMapper;
import com.ruoyi.system.domain.Putindian;
import com.ruoyi.system.service.IPutindianService;
import com.ruoyi.common.core.text.Convert;

/**
 * 投放点Service业务层处理
 * 
 * @author lwt
 * @date 2021-01-22
 */
@Service
public class PutindianServiceImpl implements IPutindianService 
{
    @Autowired
    private PutindianMapper putindianMapper;

    /**
     * 查询投放点
     * 
     * @param putinId 投放点ID
     * @return 投放点
     */
    @Override
    public Putindian selectPutindianById(Long putinId)
    {
        return putindianMapper.selectPutindianById(putinId);
    }

    /**
     * 查询投放点列表
     * 
     * @param putindian 投放点
     * @return 投放点
     */
    @Override
    public List<Putindian> selectPutindianList(Putindian putindian)
    {
        return putindianMapper.selectPutindianList(putindian);
    }

    @Override
    public List<Putindian> toufangdianchazhao(Long plotid) {
        return putindianMapper.toufangdianchazhao(plotid);
    }

    /**
     * 新增投放点
     * 
     * @param putindian 投放点
     * @return 结果
     */
    @Override
    public int insertPutindian(Putindian putindian)
    {
        putindian.setCreateTime(DateUtils.getNowDate());
        return putindianMapper.insertPutindian(putindian);
    }

    /**
     * 修改投放点
     * 
     * @param putindian 投放点
     * @return 结果
     */
    @Override
    public int updatePutindian(Putindian putindian)
    {
        return putindianMapper.updatePutindian(putindian);
    }

    /**
     * 删除投放点对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePutindianByIds(String ids)
    {
        return putindianMapper.deletePutindianByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除投放点信息
     * 
     * @param putinId 投放点ID
     * @return 结果
     */
    @Override
    public int deletePutindianById(Long putinId)
    {
        return putindianMapper.deletePutindianById(putinId);
    }
}
