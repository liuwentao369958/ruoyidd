package com.ruoyi.system.controller;

import java.util.List;
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
import com.ruoyi.system.domain.Putindian;
import com.ruoyi.system.service.IPutindianService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 投放点Controller
 * 
 * @author lwt
 * @date 2021-01-22
 */
@Controller
@RequestMapping("/system/putindian")
public class PutindianController extends BaseController
{
    private String prefix = "system/putindian";

    @Autowired
    private IPutindianService putindianService;

    @RequiresPermissions("system:putindian:view")
    @GetMapping()
    public String putindian()
    {
        return prefix + "/putindian";
    }

    /**
     * 查询投放点列表
     */
    @RequiresPermissions("system:putindian:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Putindian putindian)
    {
        startPage();
        List<Putindian> list = putindianService.selectPutindianList(putindian);
        return getDataTable(list);
    }

    /**
     * 导出投放点列表
     */
    @RequiresPermissions("system:putindian:export")
    @Log(title = "投放点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Putindian putindian)
    {
        List<Putindian> list = putindianService.selectPutindianList(putindian);
        ExcelUtil<Putindian> util = new ExcelUtil<Putindian>(Putindian.class);
        return util.exportExcel(list, "putindian");
    }

    /**
     * 新增投放点
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存投放点
     */
    @RequiresPermissions("system:putindian:add")
    @Log(title = "投放点", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Putindian putindian)
    {
        return toAjax(putindianService.insertPutindian(putindian));
    }

    /**
     * 修改投放点
     */
    @GetMapping("/edit/{putinId}")
    public String edit(@PathVariable("putinId") Long putinId, ModelMap mmap)
    {
        Putindian putindian = putindianService.selectPutindianById(putinId);
        mmap.put("putindian", putindian);
        return prefix + "/edit";
    }

    /**
     * 修改保存投放点
     */
    @RequiresPermissions("system:putindian:edit")
    @Log(title = "投放点", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Putindian putindian)
    {
        return toAjax(putindianService.updatePutindian(putindian));
    }

    /**
     * 删除投放点
     */
    @RequiresPermissions("system:putindian:remove")
    @Log(title = "投放点", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(putindianService.deletePutindianByIds(ids));
    }

    @GetMapping(value = {"/toufang/{treeid}", "/toufang/"})
    @ResponseBody
    public String toufang(@PathVariable(value = "treeid", required = false) Long treeid, ModelMap mmap) {
        List<Putindian> map = putindianService.toufangdianchazhao(treeid);
        if(map == null) {
            return "{}";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("{ ");
        for (int a = 0;a<map.size();a++){
            sb.append("\"PutinId"+a+"\":\""+map.get(a).getPutinId()+"\",");
        }
        sb.replace(sb.length()-1, sb.length(), "}");
        System.out.println(sb.toString());
        return sb.toString();
    }

}
