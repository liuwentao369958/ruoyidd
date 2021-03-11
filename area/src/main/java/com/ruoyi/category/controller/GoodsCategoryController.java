package com.ruoyi.category.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.category.domain.GoodsCategory;
import com.ruoyi.category.service.IGoodsCategoryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品分类Controller
 * 
 * @author LWT
 * @date 2021-01-13
 */
@Controller
@RequestMapping("/category/table")
public class GoodsCategoryController extends BaseController
{
    private String prefix = "category/table";

    @Autowired
    private IGoodsCategoryService goodsCategoryService;

    @RequiresPermissions("category:table:view")
    @GetMapping()
    public String table()
    {
        return prefix + "/table";
    }

    /**
     * 查询商品分类树列表
     */
    @RequiresPermissions("category:table:list")
    @PostMapping("/list")
    @ResponseBody
    public List<GoodsCategory> list(GoodsCategory goodsCategory)
    {
        List<GoodsCategory> list = goodsCategoryService.selectGoodsCategoryList(goodsCategory);
        return list;
    }

    /**
     * 新增商品分类
     */
    @GetMapping(value = { "/add/{projectId}", "/add/" })
    public String add(@PathVariable(value = "projectId", required = false) Long projectId, ModelMap mmap,HttpServletRequest request)
    {
        if (StringUtils.isNotNull(projectId))
        {
            mmap.put("goodsCategory", goodsCategoryService.selectGoodsCategoryById(projectId));
        }
        request.getSession().removeAttribute("images");
        return prefix + "/add";
    }
    /*
     * bootstarp-fileinput上传文件处理
     */
    @RequestMapping(value="/images",method={RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> addImg(@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> result= new HashMap<String, Object>();
        try{
                // 上传文件路径
                String filePath = RuoYiConfig.getUploadPath();
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                request.getSession().setAttribute("images", fileName);
                return AjaxResult.warn("上传成功");
        }catch(IOException e){
            result.put("msg","出错了");
            result.put("result",false);
            e.printStackTrace();
        }catch (Exception e1){
            e1.printStackTrace();
        }
        return result;
    }

    //点击清除按钮，清除缓存
    @RequestMapping(value="/eliminate")
    @ResponseBody
    public Map<String,Object> eliminate(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        if (request.getSession().getAttribute("images") !=null ||"".equals(request.getSession().getAttribute("images"))) {
            request.getSession().removeAttribute("images");
            return AjaxResult.warn("已清除");
        }else{
            request.getSession().setAttribute("images",null);
        }
        map.put("msg","未上传图片，现有图片已清除");
        return map;
    }

    /**
     * 新增保存商品分类
     */
    @RequiresPermissions("category:table:add")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GoodsCategory goodsCategory, HttpServletRequest request)
    {
        String username = String.valueOf(request.getSession().getAttribute("username"));
        goodsCategory.setCreateBy(username);
        if (request.getSession().getAttribute("images") == null || "".equals(request.getSession().getAttribute("images"))){
            goodsCategory.setImages("null");
        }else {
            String images = String.valueOf(request.getSession().getAttribute("images"));
            goodsCategory.setImages(images);
        }
        return toAjax(goodsCategoryService.insertGoodsCategory(goodsCategory));
    }

    /**
     * 修改商品分类
     */
    @GetMapping("/edit/{projectId}")
    public String edit(@PathVariable("projectId") Long projectId, ModelMap mmap,HttpServletRequest request)
    {
        GoodsCategory goodsCategory = goodsCategoryService.selectGoodsCategoryById(projectId);
        mmap.put("goodsCategory", goodsCategory);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品分类
     */
    @RequiresPermissions("category:table:edit")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GoodsCategory goodsCategory,HttpServletRequest request)
    {
        String username = String.valueOf(request.getSession().getAttribute("username"));
        goodsCategory.setImages(String.valueOf(request.getSession().getAttribute("images")));
        goodsCategory.setUpdateBy(username);
        return toAjax(goodsCategoryService.updateGoodsCategory(goodsCategory));
    }

    /**
     * 删除
     */
    @RequiresPermissions("category:table:remove")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{projectId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("projectId") Long projectId)
    {
        if (goodsCategoryService.selectcategoryCount(projectId) > 0)
        {
            return AjaxResult.warn("存在下级目录,不允许删除");
        }
        return toAjax(goodsCategoryService.deleteGoodsCategoryById(projectId));
    }

    /**
     * 选择商品分类树
     */
    @GetMapping(value = { "/selectTableTree/{projectId}", "/selectTableTree/" })
    public String selectTableTree(@PathVariable(value = "projectId", required = false) Long projectId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(projectId))
        {
            mmap.put("goodsCategory", goodsCategoryService.selectGoodsCategoryById(projectId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载商品分类树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = goodsCategoryService.selectGoodsCategoryTree();
        return ztrees;
    }
}
