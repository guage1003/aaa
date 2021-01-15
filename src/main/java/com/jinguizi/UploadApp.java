package com.jinguizi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Title: UploadApp
 * @Description:
 * @Auther: liupengxiang
 * @Version: 1.0
 * @create 2020/12/23  9:35
 */
@SpringBootApplication
@ServletComponentScan("com.jinguizi.config")
public class UploadApp {
    public static void main(String[] args) {
        SpringApplication.run(UploadApp.class,args);
    }
}
