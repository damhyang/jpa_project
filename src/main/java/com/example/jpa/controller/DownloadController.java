package com.example.jpa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jpa.repository.FileUploadRepository;

@Controller
public class DownloadController {
    @Autowired
    FileUploadRepository fileUploadRepository;

    // @GetMapping("/download")
    // public ResponseEntity<Resource> download() throws Exception{
    //     String downloadFolder="C:/work/data/";
    //     String oName="hamster2.jpg";
    //     File file=new  File(downloadFolder+oName);

    //     InputStreamResource resource=new InputStreamResource(new FileInputStream(file));
        
    //     return ResponseEntity.ok().header("content-disposition", "filename="+URLEncoder.encode(file.getName(),"utf-8"))
    //         .contentLength(file.length())
    //         .contentType(MediaType.parseMediaType("application/octet-stream"))
    //         .body(resource);
    // }
    // @GetMapping("/download")
    // public ResponseEntity<Resource> download() throws Exception{
    //     String downloadFolder="C:/work/data/";
    //     String oName="hamster2.jpg";
    //     File file=new  File(downloadFolder+oName);

    //     InputStreamResource resource=new InputStreamResource(new FileInputStream(file));
        
    //     return ResponseEntity.ok().header("content-disposition", "filename="+URLEncoder.encode("hamster100.jpg","utf-8"))
    //         .contentLength(file.length())
    //         .contentType(MediaType.parseMediaType("application/octet-stream"))
    //         .body(resource);
    // }
        @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("uid") String uid) throws Exception{
        
        String downloadFolder="C:/work/data/";
        String oName=fileUploadRepository.findByUuid(uid).getOriginalFileName();
        File file=new  File(downloadFolder+uid);

        InputStreamResource resource=new InputStreamResource(new FileInputStream(file));
        
        return ResponseEntity.ok().header("content-disposition", "filename="+URLEncoder.encode(oName,"utf-8"))
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(resource);
    }
}
