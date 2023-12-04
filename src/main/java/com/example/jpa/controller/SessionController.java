package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.example.jpa.model.Member;
import com.example.jpa.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class SessionController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/login")
    public String login(){
        return "html/login";
    }
    // @PostMapping("/login")
    // public String loginPost(Member member, HttpSession session){
    //     session.setAttribute("memberObject", member);
    //     return "redirect:/main";
    // }

    @PostMapping("/login")
    public String loginPost(@RequestParam("memberId") String memberId
                            ,@RequestParam("memberPw") String memberPw
                            ,HttpSession session){
        Member member;
        //"select * from member where member_id='memberId' and member_pw='memberPw'"
        member=memberRepository.findByMemberIdAndMemberPw(memberId, memberPw);
        int count=memberRepository.findByMemberPwAndMemberId(memberPw, memberId).size();
        if(count<1){
            return "/html/loginfail";
        }
        System.out.println(count);
        session.setAttribute("memberObject", member);
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main() {
        return "html/main";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/main";
    }

    @GetMapping("/resister")
    public String resister(HttpSession session){
        Member member=new Member();
        member.setMemberId("admin");
        session.setAttribute("memberObject", member);
        return "html/resister";
    }
    @PostMapping("/resister")
    //@ResponseBody
    public String resisterPost(@RequestParam("memberId") String memberId
                            ,@RequestParam("memberPw") String memberPw
                            ,@RequestParam("memberName") String memberName
                            ,HttpSession session){
        Member member=new Member();

        member.setMemberId(memberId);
        member.setMemberPw(memberPw);
        member.setMemberName(memberName);
        //"INSERT INTO member(member_id, member_pw, member_name) VALUES(memberId,memberPw,memberName);"
        //return member.getMemberName().toString()+"님의 회원가입이 완료되었습니다.";
        session.setAttribute("memberObject", member);
        if(memberRepository.findByMemberId(memberId).size()>0){
            System.out.println(member);
            return "/html/resister";
        }
        memberRepository.save(member);
        return "redirect:/main"; 
    }
}
