/*
 * FileName：UploadUtil.java
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
package com.ruoyi.common.config.utils;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Random;

/**
 * 上传工具类
 */
@Component
public class UploadUtil {

    /**
     * 文件名称
     */
    public static final String FILE_NAME = "fileName";

    /**
     * 文件原始名称
     */
    public static final String ORIGINAL_FILENAME = "OriginalFilename";
    /**
     * 文件全路径
     */
    public static final String RES_URL = "resURL";

    /**
     * 文件本地绝对路径url
     */
    public static final String FILE_PATH = "filePath";

    /**
     * 文件大小
     */
    public static final String FILE_SIZE = "fileSize";

    /**
     * 文件类别
     */
    public static final String FILE_TYPE = "fileType";

   // @Value("${res.upload.path}")
    @Value("${ruoyi.profile}")
    private  String res_upload_path;

    //@Value("${res.upload.url}")
    @Value("${ruoyi.uploadUrl}")
    private  String res_upload_url;




    /**
     * 获取文件路径
     *
     * @param ext
     * @return
     */
    public static String getFileName(String ext) {
        return System.currentTimeMillis() + new Random().nextInt(10000) + "." + ext;
    }

    /**
     * 获取根路徑
     *
     * @return
     */
    public  String getFileBasePath() {
        String filePath = null;
        try {
            filePath = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
            //读取配置文上传件的路径
            /*if (PropertiesUtil.getString("res.upload.path") != null) {
                filePath = PropertiesUtil.getString("res.upload.path");
            }*/
            if (null != res_upload_path) {
                filePath =res_upload_path;
            }else {
                filePath = filePath + "/upload/";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filePath;
    }

    /**
     * 获取文件路径
     *
     * @param fileName
     * @return
     */
    public  String getFilePath(String fileName) {

        return getFileBasePath() + fileName;
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public  Map<String, String> uploadFile(MultipartFile file) throws Exception {
        //原文件名称
        String OriginalFilename = file.getOriginalFilename();
        //文件后缀名
        String ext = FilenameUtils.getExtension(OriginalFilename);
        //系统生成的文件名
        String fileName = getFileName(ext);
        //图片上传路径
        String resURL =res_upload_url;
                // PropertiesUtil.getString("res.upload.url");
        String filePath = getFilePath(fileName);
        File saveFile = new File(filePath);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        file.transferTo(saveFile);
        return new ImmutableMap.Builder<String, String>()
                .put(ORIGINAL_FILENAME, OriginalFilename)
                .put(FILE_NAME, fileName)
                .put(RES_URL, resURL + fileName)
                .put(FILE_PATH, filePath)
                .put(FILE_SIZE, String.valueOf(file.getSize()))
                .put(FILE_TYPE, ext).build();
    }
}
