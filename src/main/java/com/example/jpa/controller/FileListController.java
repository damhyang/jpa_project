package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.jpa.model.UploadFile;
import com.example.jpa.repository.FileUploadRepository;
import com.example.jpa.repository.MemberRepository;

@Controller
public class FileListController {
    @Autowired
    FileUploadRepository fileUploadRepository;

    UploadFile uploadFile;

    @GetMapping("/filelist")
    public String fileList(Model model){
        model.addAttribute("uploadfileObject", fileUploadRepository.findAll());
        return "/html/fileList";
    }
}
