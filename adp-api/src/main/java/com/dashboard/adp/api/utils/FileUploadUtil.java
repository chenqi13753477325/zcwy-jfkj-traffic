package com.dashboard.adp.api.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/**
 * SpringBoot上传文件工具类
 * @author LinkinStar
 */
public class FileUploadUtil {

    /** 绝对路径 **/
    private static String absolutePath = "";

    /** 静态目录 **/
    private static String staticDir = "static";

    /** 文件存放的目录 **/
    private static String fileDir = "/upload/";

    /**
     * 上传单个文件
     * 最后文件存放路径为：static/upload/image/test.jpg
     * 文件访问路径为：http://127.0.0.1:8080/upload/image/test.jpg
     * 该方法返回值为：/upload/image/test.jpg
     * @param inputStream 文件流
     * @param path 文件路径，如：image/
     * @param filename 文件名，如：test.jpg
     * @return 成功：上传后的文件访问路径，失败返回：null
     */
    public static String upload(InputStream inputStream, String path, String filename) {
        //第一次会创建文件夹
        createDirIfNotExists();

        String resultPath = fileDir + path + filename;

        //存文件
        File uploadFile = new File(absolutePath, staticDir + resultPath);
        try {
            FileUtils.copyInputStreamToFile(inputStream, uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return resultPath;
    }

    /**
     * 创建文件夹路径
     */
    private static void createDirIfNotExists() {
        if (!absolutePath.isEmpty()) {return;}

        //获取跟目录
        File file = null;
        try {
            file = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("获取根目录失败，无法创建上传目录！");
        }
        if(!file.exists()) {
            file = new File("");
        }

        absolutePath = file.getAbsolutePath();

        File upload = new File(absolutePath, staticDir + fileDir);
        if(!upload.exists()) {
            upload.mkdirs();
        }
    }

    /**
     * 删除文件
     * @param path 文件访问的路径upload开始 如： /upload/image/test.jpg
     * @return true 删除成功； false 删除失败
     */
    public static boolean delete(String path) {
        File file = new File(absolutePath, staticDir + path);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static Map<String,String> uploadAttachAndReturnUrl(MultipartFile file) {
        Map<String,String> map = new HashMap();
        String oldFileName = file.getOriginalFilename();
        String prefix = FilenameUtils.getExtension(oldFileName);
        int filesize = 200*1024000;
        if (file.getSize() > filesize) {
            // 图片格式:bmp,jpg,png,gif,jpeg,pneg
            throw new RuntimeException("附件大小超出预设大小200M");
        } else {
            // UUID解决的文件同名问题。乱码问题
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String oldName = file.getOriginalFilename();
            String ext = oldName.substring(oldName.lastIndexOf("."));
            // 组合成新的文件名
            String newFileName = new StringBuffer(uuid).append(oldName).toString();
            String url = "";
            try{
                url = FileUploadUtil.upload(file.getInputStream(), "", newFileName);
            }catch(Exception e){
                try {
                    throw new Exception("上传操作出现错误");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            map.put("ext",ext);
            map.put("url",url);
            return map;
        }
    }
    public static void downloadAttach(String url, HttpServletResponse resp) {
        DataInputStream in = null;
        OutputStream out = null;
        String finalName = url.substring(url.indexOf("/")+1);
        try{
            resp.reset();// 清空输出流
            url = ConstantAddressUtil.attachdownloadAddress + url;
            finalName = URLEncoder.encode(finalName,"UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Content-disposition", "attachment; filename=" + finalName );// 设定输出文件头
            resp.setContentType("application/msexcel");// 定义输出类型
            //输入流：本地文件路径
            in = new DataInputStream(
                    new FileInputStream(new File(url)));
            //输出流
            out = resp.getOutputStream();
            //输出文件
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
        } catch(Exception e){
            e.printStackTrace();
            resp.reset();
            try {
                OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream(), "UTF-8");
                String data = "<script language='javascript'>alert(\"\\u64cd\\u4f5c\\u5f02\\u5e38\\uff01\");</script>";
                writer.write(data);
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }finally {
            if(null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}