package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.PutindianMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CabinetMapper;
import com.ruoyi.system.domain.Cabinet;
import com.ruoyi.system.service.ICabinetService;
import com.ruoyi.common.core.text.Convert;

/**
 * 货柜管理Service业务层处理
 * 
 * @author lwt
 * @date 2021-01-21
 */
@Service
public class CabinetServiceImpl implements ICabinetService 
{
    @Autowired
    private CabinetMapper cabinetMapper;
    /**
     * 查询货柜管理
     * 
     * @param cabinetId 货柜管理ID
     * @return 货柜管理
     */
    @Override
    public Cabinet selectCabinetById(Long cabinetId)
    {
        return cabinetMapper.selectCabinetById(cabinetId);
    }

    /**
     * 查询货柜管理列表
     * 
     * @param cabinet 货柜管理
     * @return 货柜管理
     */
    @Override
    public List<Cabinet> selectCabinetList(Cabinet cabinet)
    {
        return cabinetMapper.selectCabinetList(cabinet);
    }

    /**
     * 新增货柜管理
     * 
     * @param cabinet 货柜管理
     * @return 结果
     */
    @Override
    public int insertCabinet(Cabinet cabinet)
    {
        Cabinet info = cabinetMapper.selectCabinetById(cabinet.getFatherCabinetid());
        if(info==null){
            cabinet.setCreateTime(DateUtils.getNowDate());
            return cabinetMapper.insertCabinet(cabinet);
        }
        cabinet.setCreateTime(DateUtils.getNowDate());
      cabinet.setAncestors(info.getAncestors() + "," + cabinet.getFatherCabinetid());
        return cabinetMapper.insertCabinet(cabinet);
    }

    /**
     * 修改货柜管理
     * 
     * @param cabinet 货柜管理
     * @return 结果
     */
    @Override
    public int updateCabinet(Cabinet cabinet)
    {
        Cabinet info = cabinetMapper.selectCabinetById(cabinet.getFatherCabinetid());
        if(info==null){
            return cabinetMapper.updateCabinet(cabinet);
        }
        cabinet.setAncestors(info.getAncestors() + "," + cabinet.getFatherCabinetid());
        return cabinetMapper.updateCabinet(cabinet);
    }

    @Override
    public Cabinet selectbianhao(long cabinetid) {
        Cabinet a = cabinetMapper.selectbianhao(cabinetid);
        return a;
    }

    @Override
    public int selectcabineTCount(long cabinetId) {
            Cabinet chinaAreaTable = new Cabinet();
            chinaAreaTable.setCabinetId(cabinetId);
            return cabinetMapper.selectcabineTCount(chinaAreaTable);

    }

    /**
     * 删除货柜管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCabinetByIds(String ids)
    {
        return cabinetMapper.deleteCabinetByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除货柜管理信息
     * 
     * @param cabinetId 货柜管理ID
     * @return 结果
     */
    @Override
    public int deleteCabinetById(Long cabinetId)
    {
        return cabinetMapper.deleteCabinetById(cabinetId);
    }

    /**
     * 查询货柜管理树列表
     * 
     * @return 所有货柜管理信息
     */
    @Override
    public List<Ztree> selectCabinetTree()
    {
        List<Cabinet> cabinetList = cabinetMapper.selectCabinetList(new Cabinet());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (Cabinet cabinet : cabinetList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(cabinet.getCabinetId());
            ztree.setpId(cabinet.getFatherCabinetid());
            ztree.setName(cabinet.getCabinetBianha());
            ztree.setTitle(cabinet.getCabinetBianha());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
