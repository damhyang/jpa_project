package com.example.jpa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

//import com.example.jpa.model.FileInfo;
import com.example.jpa.model.Member;
import com.example.jpa.model.Picture;
//import com.example.jpa.model.Picture;
import com.example.jpa.repository.MemberRepository;
import com.example.jpa.repository.PictureRepository;

@Controller
public class MyInfo {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PictureRepository pictureRepository;

    @GetMapping("/myinfo")
    public String MyInfomation(Model model
                        ,@RequestParam("memberId") String memberId){
        Member member;
        Picture picture;
        member=memberRepository.findByMemberId(memberId).get(0); //list형태로 받아와서 오브젝트를 뽑기 위해 get(0)을 씀.
        picture=pictureRepository.findByMemberId(memberId).get(0); 
        model.addAttribute("memberObject", member);
        model.addAttribute("pictureObject", picture);
        return "/html/myinformation";
    }
}
