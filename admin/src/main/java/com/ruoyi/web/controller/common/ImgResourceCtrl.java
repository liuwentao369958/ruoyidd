/*
 * FileName：ImgResourceCtrl.java
 * <p>
 * Copyright (c) 2017-2020 <a href="https://www.smartwx.info">hermit(1154808491@qq.com)</a>.
 * <p>
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl-3.0.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.ruoyi.web.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.config.utils.UploadUtil;
import com.ruoyi.common.ueditor.ActionEnter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/**
 * @author : hermit
 */
@Controller
@RequestMapping("/managerImg")
public class ImgResourceCtrl {

    private Logger logger = LoggerFactory.getLogger(ImgResourceCtrl.class);

    @Autowired
    private UploadUtil uploadUtil;

    /**
     * ueditor配置文件名称
     */
    @Value("${uEditorConfig.fileName}")
    private String configFileName;

    /**
     * 上传图片(layui富文本)
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("uploadFile")
    public Map uploadFile(MultipartFile file) throws Exception {
        //上传
        Map<String, String> stringMap = uploadUtil.uploadFile(file);
        //构造返回参数
        Map<String, Object> map = Maps.newHashMap();
        Map<String, Object> mapData = Maps.newHashMap();
        map.put("code", 0);//0表示成功，1失败
        map.put("msg", "上传成功");//提示消息
        map.put("data", mapData);//提示消息
        mapData.put("src", stringMap.get(UploadUtil.RES_URL));//图片url
        mapData.put("title", stringMap.get(UploadUtil.FILE_NAME));//图片名称，这个会显示在输入框里
        return map;
    }

    /**
     * 上传图片(百度富文件上传)
     *
     * @return
     */
    @RequestMapping("ueditor")
    public void ueditorUpload(String action, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        if ("config".equals(action)) {    //如果是初始化
           // response.getWriter().write(new ActionEnter(request, ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath()).exec());
            String exec = new ActionEnter(request, rootPath,configFileName).exec();
            response.getWriter().write(exec);
        } else if ("uploadimage".equals(action) || "uploadvideo".equals(action) || "uploadfile".equals(action) || "uploadscrawl".equals(action)) {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            JSONObject jsonObject = new JSONObject();
            if (multipartResolver.isMultipart(request)) {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();

                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    if (file != null) {
                        Map<String, String> uploadFile = uploadUtil.uploadFile(file);
                        if (!uploadFile.isEmpty()) {
                            jsonObject.put("state", "SUCCESS");
                            jsonObject.put("original", UploadUtil.ORIGINAL_FILENAME);//原来的文件名
                            jsonObject.put("size", uploadFile.get(UploadUtil.FILE_SIZE));
                            jsonObject.put("title", uploadFile.get(UploadUtil.ORIGINAL_FILENAME));
                            jsonObject.put("type", uploadFile.get(UploadUtil.FILE_TYPE));
                            jsonObject.put("url", uploadFile.get(UploadUtil.RES_URL));
                        }
                        response.getWriter().write(jsonObject.toString());
                    }
                }

            }
        } else if ("catchimage".equals(action)) {
            //todo 抓取远程图片(待实现)
        } else if ("listimage".equals(action) || "listfile".equals(action)) {
            //todo 列出指定目录下的图片/文件(待实现)
        }
    }

    /**
     * 上传图片(普通上传)
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("uploadFileImg")
    public AjaxResult uploadFileImg(MultipartFile file) throws Exception {
        //上传
        Map<String, String> stringMap = uploadUtil.uploadFile(file);
        return AjaxResult.success(stringMap.get(UploadUtil.RES_URL));
    }


}
