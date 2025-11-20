package com.yueyo.yueyoraicodemother;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yueyo.yueyoraicodemother.mapper")
public class YueyorAiCodeMotherApplication {

    public static void main(String[] args) {
        SpringApplication.run(YueyorAiCodeMotherApplication.class, args);
    }

}
