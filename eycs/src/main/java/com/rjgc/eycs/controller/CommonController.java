package com.rjgc.eycs.controller;

import com.rjgc.eycs.common.R;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 软件工程2003陈俊文
 * 学号20201002992
 * 第周作业
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${eycs.path}")
    private  String  basePath;
//文件上传
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        log.info(file.toString());
        //file是一个临时文件，需要转存 在指定位置，否则本次请求完成后临时文件会被删除
        //原始文件名取出其中后缀
        String originalFilename=file.getOriginalFilename();
        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用UUID重新生成文件名，防止文件名重复造成文件覆盖
        String filename= UUID.randomUUID().toString()+suffix;
        //创建一个目录对象
        File dir=new File(basePath);
        //判断目录d:\eycs-img\是否存在
        if(!dir.exists()){
            //目录不存在，需要创建
            dir.mkdir();
        }
        try{
            //将临时文件送入d盘存储
            file.transferTo(new File(basePath+filename));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return  R.success(filename);
    }

    //文件下载
    @GetMapping("/download")
    public  void download(String name, HttpServletResponse response){
       try{
           //输入流，通过输入流读取文件内容
           FileInputStream fileInputStream=new FileInputStream(new File(basePath+name));
           //输出流，通过输出流将文件写回游览器，在游览器展示图片
           ServletOutputStream outputStream=response.getOutputStream();
           response.setContentType("image/jpeg");
           int len=0;
           byte[]bytes=new  byte[1024];
           while((len=fileInputStream.read(bytes))!=-1){
               outputStream.write(bytes,0,len);
               outputStream.flush();;

           }
           //关闭资源
           fileInputStream.close();
           outputStream.close();
       }
       catch (Exception e){
           e.printStackTrace();
       }


    }

}
