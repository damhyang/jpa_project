package com.example.jpa.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jpa.model.Demo;
import com.example.jpa.repository.DemoRepository;

@Controller
public class HtmlController {
    @Autowired
    DemoRepository demoRepository;

    @GetMapping("/home")
    public String home(Model model){
        List<String> list = new ArrayList<>();
        list.add("list1");
        list.add("list2");
        list.add("list3");
        model.addAttribute("listObject", list);
//List<String> Sting값이 모여있는 리스트


        Map<String, Object> map = new HashMap<>();
        map.put("key1","map1");
        map.put("key2","map2");
        model.addAttribute("mapObject", map);

        // Demo demo=new Demo();
        // demo.setUser("김담향");
        // model.addAttribute("demoObject", demo.getUser());

        List<Demo> demo = demoRepository.findAll();
        model.addAttribute("demoObject", demo);

        return "/html/home";
    }

    @GetMapping("/user1")
    public String user(Model model) {
        Map<String, Object> user = null;
        user = new HashMap<>();
        user.put("userId", "z");
        user.put("userName", "zoo");
        user.put("userAge", 25);
        model.addAttribute("user", user);
        return "/html/user";
    }

    @GetMapping("/userList")
    public String userList(Model model) {
        List<Demo> demo = demoRepository.findAll();
        model.addAttribute("userList", demo);
        return "/html/userList";
    }

    @GetMapping("/mode")
    public String mode(Model model) {
        Demo mode = demoRepository.findByUser("bbb");
        model.addAttribute("mode", mode);
        return "/html/mode";
    }

    @GetMapping("/pagination")
    public String pagination(
        Model model
        ,@RequestParam(defaultValue="1") int page   //파라미터 2개
    ){
        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        return "/html/pagination";
    }

    @GetMapping("/linkurl")
    public String linkUrl(
        Model model
        ,@RequestParam(defaultValue="1") int page
    ){
        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = startPage + 9;
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("page", page);
        return "/html/rinkurl";
    }
}
