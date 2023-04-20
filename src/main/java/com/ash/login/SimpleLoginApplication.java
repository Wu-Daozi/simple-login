package com.ash.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wu dz
 * @date 2023/4/19
 */
@SpringBootApplication
@MapperScan("com.ash.login.mapper.**")
public class SimpleLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleLoginApplication.class, args);
    }

}
