package com.gg.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gg.server.mapper")
public class IhisDoctorApplication {

    public static void main(String[] args) {
        SpringApplication.run(IhisDoctorApplication.class,args);
    }
}
