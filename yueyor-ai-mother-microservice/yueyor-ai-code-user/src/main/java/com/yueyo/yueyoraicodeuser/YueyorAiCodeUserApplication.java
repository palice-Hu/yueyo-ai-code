package com.yueyo.yueyoraicodeuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.yueyo.yueyoraicodeuser.mapper")
@ComponentScan("com.yueyo")
//@EnableDubbo
public class YueyorAiCodeUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(YueyorAiCodeUserApplication.class, args);
    }
}
