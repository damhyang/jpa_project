package com.example.jpa.controller;

import java.io.File;
import java.io.IOException;
//import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.jpa.repository.FileUploadRepository;
import com.example.jpa.repository.PictureRepository;
import com.example.jpa.util.getCurrentTime;

import jakarta.servlet.http.HttpServletRequest;

import com.example.jpa.model.FileInfo;
import com.example.jpa.model.Picture;
import com.example.jpa.model.UploadFile;


@Controller
public class UploadController {
    @Autowired
    FileUploadRepository fileUploadRepository;

    @Autowired
    PictureRepository pictureRepository;

    @GetMapping("/upload1")
    public String upload1(){
        return "/html/upload1";
    }

    @PostMapping("/upload1")
    @ResponseBody
    public String upload1Post(MultipartHttpServletRequest mRequest){
        String result="";
        MultipartFile mFile=mRequest.getFile("file1");
        String oName=mFile.getOriginalFilename();
        result+= oName + "<br>" + mFile.getSize();
        mFile.getContentType();
        System.out.println(mFile.getContentType());
        String saveFolder = "C:/work/data/";
        File saveFile=new File(saveFolder+oName); //저장할 경로와 이름 스트링으로 "c:\\dfdfd\fdfd"
        try {
            mFile.transferTo(saveFile);
        // } catch (IllegalStateException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        }  catch (IllegalStateException|IOException e) {
            e.printStackTrace();
         }
        return result;
    }

    @GetMapping("/upload2")
    public String upload2(){
        return "/html/upload2";
    }

    @PostMapping("/upload2")
    @ResponseBody
    public String upload2Post(@RequestParam("file2") MultipartFile mFile
                             ,@RequestParam("memberId") String memberId
                             ,HttpServletRequest request){
        UploadFile uploadFile=new UploadFile();
        String regDate=getCurrentTime.getTime(); //static으로 getCurrentTime이 되어 있음.
        // String currentTime=LocalDateTime.now().toString();
        // String regDate=currentTime.substring(0, 10)+"  "+currentTime.substring(11, 19);
        //String result="";
        String uid=UUID.randomUUID().toString();
        String oName=mFile.getOriginalFilename();
        // result+= oName + "<br>" + mFile.getSize() +"<br>"+ uid;
        // if (secretFlag==null){
        //     secretFlag="1";
        // }
        //String secretFlag=request.getParameter("secretFlag");
        String secretFlag="";
        if(request.getParameter("secretFlag")==null){
            secretFlag="1";
        }else{
            secretFlag=request.getParameter("secretFlag");
        }
        //System.out.println(secretFlag);
        String saveFolder ="C:/myspring/jpa/jpa/src/main/resources/static/image/";
        //String saveFolder = "C:/work/data/";
        //File saveFile=new File(saveFolder+oName);
        File saveFile=new File(saveFolder+uid);
        try {
            int seq=fileUploadRepository.findAll().size()+1;
            uploadFile.setOriginalFileName(oName);
            uploadFile.setSeq(seq);
            uploadFile.setUuid(uid);
            uploadFile.setRegDate(regDate);
            uploadFile.setMemberId(memberId);
            uploadFile.setSecretFlag(secretFlag);
            fileUploadRepository.save(uploadFile);
            mFile.transferTo(saveFile);
        }  catch (IllegalStateException|IOException e) {
            e.printStackTrace();
        }
        //return result;
        return oName+"성공적으로 저장되었습니다.";
    }

    @GetMapping("/pictureupload")
    public String pictureUpload(){
        return "html/pictureupload";
    }

    @PostMapping("/pictureupload")
    @ResponseBody
    public String pictureUploadPost(@RequestParam("file2") MultipartFile mFile
                                   ,@RequestParam("memberId") String memberId){
        Picture picture=new Picture();
        String updateDate=getCurrentTime.getTime();
        String fileName=mFile.getOriginalFilename();
        String memberFileName=memberId+"_"+fileName;
        String saveFolder="C:/myspring/jpa/jpa/src/main/resources/static/image/";
        File saveFile=new File(saveFolder+memberFileName);
        int seq=pictureRepository.findAll().size()+1;
        try {
            mFile.transferTo(saveFile);
            picture.setSeq(seq);
            picture.setMemberId(memberId);
            picture.setFileName(memberFileName);
            picture.setUpdateDate(updateDate);
            pictureRepository.save(picture);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "<div><img src='image/"+memberFileName+"'></div>";
    }

    @GetMapping("/upload3")
    public String upload3() {
        return "/html/upload3";
    }
    @PostMapping("/upload3")
    @ResponseBody
    public String upload3Post(@ModelAttribute FileInfo info){
        String result="";
        String oName=info.getFile().getOriginalFilename();
        Long size=info.getFile().getSize();
        String type=info.getFile().getContentType();
        result+=oName+":"+size+":"+type;
        return result;
    }

    @GetMapping("/upload4")
    public String upload4() {
        return "/html/upload4";
    }
    @PostMapping("/upload4")
    @ResponseBody
    public String upload4Post(@RequestParam("file") MultipartFile[] mFiles
                             ,@RequestParam("memberId") String memberId){
        UploadFile uploadFile=new UploadFile();
        String result = "";
        String saveFolder="C:/work/data/";
        String currentDate=getCurrentTime.getTime();

        for(MultipartFile mFile : mFiles) {
            String uid=UUID.randomUUID().toString();
            String oName = mFile.getOriginalFilename();
            int seq=fileUploadRepository.findAll().size()+1;
            File saveFile=new File(saveFolder+uid);
            uploadFile.setMemberId(memberId);
            uploadFile.setOriginalFileName(oName);
            uploadFile.setRegDate(currentDate);
            uploadFile.setSeq(seq);
            uploadFile.setUuid(uid);
            try {
                mFile.transferTo(saveFile);
                fileUploadRepository.save(uploadFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            } 

            //result += oName + " : " + mFile.getSize() + "<br>";
            result += "<p>"+oName +"</p>";
        } 
        return result;
    }
}
