package com.abhinendra;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.abhinendra.services.PersonService;


@SpringBootApplication
@EntityScan(basePackages = {"com.abhinendra.domain"})
@ComponentScan(basePackages = "com.abhinendra")
public class ServerApplication {

    @Autowired
    PersonService personService;
    
    @PostConstruct
    public void readFile(){
    	personService.readFile("sample.txt");
    }
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
