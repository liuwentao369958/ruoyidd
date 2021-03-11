package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.CommunityManagement;
import com.ruoyi.system.service.ICommunityManagementService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 小区管理Controller
 * 
 * @author LWT
 * @date 2021-01-13
 */
@Controller
@RequestMapping("/system/management")
public class CommunityManagementController extends BaseController
{
    private String prefix = "system/management";

    @Autowired
    private ICommunityManagementService communityManagementService;

    @RequiresPermissions("system:management:view")
    @GetMapping()
    public String management()
    {
        return prefix + "/management";
    }

    /**
     * 查询小区管理树列表
     */
    @RequiresPermissions("system:management:list")
    @PostMapping("/list")
    @ResponseBody
    public List<CommunityManagement> list(CommunityManagement communityManagement)
    {
        List<CommunityManagement> list = communityManagementService.selectCommunityManagementList(communityManagement);
        return list;
    }

    /**
     * 新增小区管理
     */
    @GetMapping(value = { "/add/{plotId}", "/add/" })
    public String add(@PathVariable(value = "plotId", required = false) Long plotId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(plotId))
        {
            mmap.put("communityManagement", communityManagementService.selectCommunityManagementById(plotId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存小区管理
     */
    @RequiresPermissions("system:management:add")
    @Log(title = "小区管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CommunityManagement communityManagement, HttpServletRequest request)
    {
        String username = String.valueOf(request.getSession().getAttribute("username"));
        communityManagement.setCreateBy(username);
        return toAjax(communityManagementService.insertCommunityManagement(communityManagement));
    }

    /**
     * 修改小区管理
     */
    @GetMapping("/edit/{plotId}")
    public String edit(@PathVariable("plotId") Long plotId, ModelMap mmap)
    {
        CommunityManagement communityManagement = communityManagementService.selectCommunityManagementById(plotId);
        mmap.put("communityManagement", communityManagement);
        return prefix + "/edit";
    }

    /**
     * 修改保存小区管理
     */
    @RequiresPermissions("system:management:edit")
    @Log(title = "小区管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CommunityManagement communityManagement,HttpServletRequest request)
    {
        String username = String.valueOf(request.getSession().getAttribute("username"));
        communityManagement.setUpdateBy(username);
        return toAjax(communityManagementService.updateCommunityManagement(communityManagement));
    }

    /**
     * 删除
     */
    @RequiresPermissions("system:management:remove")
    @Log(title = "小区管理", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{plotId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("plotId") Long plotId)
    {
        if (communityManagementService.selectplotCount(plotId) > 0)
        {
            return AjaxResult.warn("存在下级目录,不允许删除");
        }
        return toAjax(communityManagementService.deleteCommunityManagementById(plotId));
    }

    /**
     * 选择小区管理树
     */
    @GetMapping(value = { "/selectManagementTree/{plotId}", "/selectManagementTree/" })
    public String selectManagementTree(@PathVariable(value = "plotId", required = false) Long plotId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(plotId))
        {
            mmap.put("communityManagement", communityManagementService.selectCommunityManagementById(plotId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载小区管理树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = communityManagementService.selectCommunityManagementTree();
        return ztrees;
    }
}
