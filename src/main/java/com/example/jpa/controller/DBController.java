package com.example.jpa.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.model.Demo;
import com.example.jpa.repository.DemoRepository;

@RestController

public class DBController {
    @Autowired
    DemoRepository demoRepository;

    @GetMapping("/")
    public List<Demo> demoSelect(){
        return demoRepository.findAll();
    }

    @GetMapping("/user")
    public List<Demo> demoSelectUser(){
        return demoRepository.findByUser("AAA");
    }

    // @GetMapping("/user2")
    // public Demo demoSelectUser2(){
    //     return demoRepository.findByUser2("AAA");
    // }

    // @GetMapping("/user3")
    // public String demoSelectUser3(){
    //     String user=demoRepository.findByUser2("AAA").getUser();
    //     return user;
    // }

    @GetMapping("/save")  //insert대신 save를 쓰는 이유는 인서트와 업데이트를 동시에 하니까.
    public Demo demoInsert(){
        Demo data=new Demo();
        data.setSeq(3);
        data.setUser("김담향");
        demoRepository.save(data);
        return data;
    }

    @GetMapping("/delete")
    public Demo demoDelete(){
        Demo data=new Demo();
        data.setSeq(3);
        demoRepository.delete(data);
        return data;
    }

}
