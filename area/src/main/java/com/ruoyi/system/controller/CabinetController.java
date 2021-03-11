package com.ruoyi.system.controller;

import java.util.List;


import com.ruoyi.system.domain.Putindian;
import com.ruoyi.system.service.IPutindianService;
import com.ruoyi.tuisong.tuisong;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Cabinet;
import com.ruoyi.system.service.ICabinetService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;
import javax.servlet.http.HttpServletRequest;

/**
 * 货柜管理Controller
 * 
 * @author lwt
 * @date 2021-01-21
 */
@Controller
@RequestMapping("/system/cabinet")
public class CabinetController extends BaseController
{
    private String prefix = "system/cabinet";

    @Autowired
    private ICabinetService cabinetService;

    @Autowired
    private IPutindianService putindianService;

    @RequiresPermissions("system:cabinet:view")
    @GetMapping()
    public String cabinet()
    {
        return prefix + "/cabinet";
    }

    /**
     * 查询货柜管理树列表
     */
    @RequiresPermissions("system:cabinet:list")
    @PostMapping("/list")
    @ResponseBody
    public List<Cabinet> list(Cabinet cabinet)
    {
        List<Cabinet> list = cabinetService.selectCabinetList(cabinet);
        return list;
    }
    /**
     * 新增货柜管理
     */
    @GetMapping(value = { "/add/{cabinetId}", "/add/" })
    public String add(@PathVariable(value = "cabinetId", required = false) Long cabinetId, ModelMap mmap, HttpServletRequest request)
    {
        Cabinet a = cabinetService.selectCabinetById(cabinetId);
        if (StringUtils.isNotNull(cabinetId))
        {
            mmap.put("cabinet", a);
        }
        if(cabinetId !=null){
            return prefix+"/addlist";
        }
        return prefix + "/add";
    }

    /**
     * 新增保存货柜管理
     */
    @RequiresPermissions("system:cabinet:add")
    @Log(title = "货柜管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Cabinet cabinet)
    {
        return toAjax(cabinetService.insertCabinet(cabinet));
    }

    /**
     * 修改货柜管理
     */
    @GetMapping("/edit/{cabinetId}")
    public String edit(@PathVariable("cabinetId") Long cabinetId, ModelMap mmap)
    {
        Cabinet cabinet = cabinetService.selectCabinetById(cabinetId);
        mmap.put("cabinet", cabinet);
        return prefix + "/edit";
    }

    /**
     * 修改保存货柜管理
     */
    @RequiresPermissions("system:cabinet:edit")
    @Log(title = "货柜管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Cabinet cabinet)
    {
        return toAjax(cabinetService.updateCabinet(cabinet));
    }

    /**
     * 删除
     */
    @RequiresPermissions("system:cabinet:remove")
    @Log(title = "货柜管理", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{cabinetId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("cabinetId") Long cabinetId)
    {
        if (cabinetService.selectcabineTCount(cabinetId) > 0)
        {
            return AjaxResult.warn("请先删掉该柜子所有的柜门，再删除柜子哦");
        }
        return toAjax(cabinetService.deleteCabinetById(cabinetId));
    }

    /**
     * 选择货柜管理树
     */
    @GetMapping(value = { "/selectCabinetTree/{cabinetId}", "/selectCabinetTree/" })
    public String selectCabinetTree(@PathVariable(value = "cabinetId", required = false) Long cabinetId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(cabinetId))
        {
            mmap.put("cabinet", cabinetService.selectCabinetById(cabinetId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载货柜管理树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = cabinetService.selectCabinetTree();
        return ztrees;
    }



    @GetMapping(value = { "/tuisong/{cabinetId}", "/tuisong/" })
    @ResponseBody
    public AjaxResult tuisong(@PathVariable(value = "cabinetId", required = false) Long cabinetId, ModelMap mmap)
    {
        Cabinet cabinet = cabinetService.selectbianhao(cabinetId);
        Putindian putindian = putindianService.selectPutindianById(cabinet.getPutinId());
        String key = putindian.getSecret();
        new tuisong().fasong(cabinetId, cabinet.getPutinId(),key);
        return AjaxResult.warn("正在开门,请稍后");
    }




}
