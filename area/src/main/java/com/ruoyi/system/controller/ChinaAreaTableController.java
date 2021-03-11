package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDept;
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
import com.ruoyi.system.domain.ChinaAreaTable;
import com.ruoyi.system.service.IChinaAreaTableService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

import javax.servlet.http.HttpServletRequest;

/**
 * 地区Controller
 * 
 * @author puzzle
 * @date 2021-01-12
 */
@Controller
@RequestMapping("/area/table")
public class ChinaAreaTableController extends BaseController
{
    private String prefix = "area/table";

    @Autowired
    private IChinaAreaTableService chinaAreaTableService;

    @RequiresPermissions("area:table:view")
    @GetMapping()
    public String table()
    {
        return prefix + "/table";
    }

    /**
     * 查询地区树列表
     */
    @RequiresPermissions("area:table:list")
    @PostMapping("/list")
    @ResponseBody
    public List<ChinaAreaTable> list(ChinaAreaTable chinaAreaTable)
    {
        List<ChinaAreaTable> list = chinaAreaTableService.selectChinaAreaTableList(chinaAreaTable);
        return list;
    }
    /**
     * 新增地区
     */
    @GetMapping(value = { "/add/{areaId}", "/add/" })
    public String add(@PathVariable(value = "areaId", required = false) Long areaId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(areaId))
        {
            mmap.put("chinaAreaTable", chinaAreaTableService.selectChinaAreaTableById(areaId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存地区
     */
    @RequiresPermissions("area:table:add")
    @Log(title = "地区", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChinaAreaTable chinaAreaTable, HttpServletRequest request)
    {
        String username = String.valueOf(request.getSession().getAttribute("username"));
        chinaAreaTable.setCreateBy(username);
        return toAjax(chinaAreaTableService.insertChinaAreaTable(chinaAreaTable));
    }

    /**
     * 修改地区
     */
    @GetMapping("/edit/{areaId}")
    public String edit(@PathVariable("areaId") Long areaId, ModelMap mmap)
    {
        ChinaAreaTable chinaAreaTable = chinaAreaTableService.selectChinaAreaTableById(areaId);
        mmap.put("chinaAreaTable", chinaAreaTable);
        return prefix + "/edit";
    }

    /**
     * 修改保存地区
     */
    @RequiresPermissions("area:table:edit")
    @Log(title = "地区", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChinaAreaTable chinaAreaTable,HttpServletRequest request)
    {
        String username = String.valueOf(request.getSession().getAttribute("username"));
        chinaAreaTable.setUpdateBy(username);
        return toAjax(chinaAreaTableService.updateChinaAreaTable(chinaAreaTable));
    }

    /**
     * 删除
     */
    @RequiresPermissions("area:table:remove")
    @Log(title = "地区", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{areaId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("areaId") Long areaId)
    {
        if (chinaAreaTableService.selectareaCount(areaId) > 0)
        {
            return AjaxResult.warn("存在下级地区,不允许删除");
        }
        return toAjax(chinaAreaTableService.deleteChinaAreaTableById(areaId));
    }

    /**
     * 选择地区树
     */
    @GetMapping(value = { "/selectTableTree/{areaId}", "/selectTableTree/" })
    public String selectTableTree(@PathVariable(value = "areaId", required = false) Long areaId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(areaId))
        {
            mmap.put("chinaAreaTable", chinaAreaTableService.selectChinaAreaTableById(areaId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载地区树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = chinaAreaTableService.selectChinaAreaTableTree();
        return ztrees;
    }
}
