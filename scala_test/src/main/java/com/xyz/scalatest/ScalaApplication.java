package com.xyz.scalatest;

import cn.gjing.tools.swagger.core.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger
public class ScalaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScalaApplication.class,args);
    }
}
