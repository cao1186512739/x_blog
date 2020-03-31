package com.xblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName XblogServerApplication
 * @Description: 应用程序启动类
 * @Author caobing
 * @Date 2020/3/19
 * @Version V1.0
 **/
@MapperScan(basePackages = "com.xblog.mapper")
@SpringBootApplication
public class XblogServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(XblogServerApplication.class, args);
    }

}
