package com.ruoyi.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},scanBasePackages = "com.ruoyi")
public class RuoYiApi {

    public static void main(String[] args) {
        SpringApplication.run(RuoYiApi.class,args);
        System.out.println("(♥◠‿◠)ﾉﾞ  若依api启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
