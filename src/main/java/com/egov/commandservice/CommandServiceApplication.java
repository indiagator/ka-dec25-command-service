package com.egov.commandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class CommandServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(CommandServiceApplication.class, args);
    }

}
