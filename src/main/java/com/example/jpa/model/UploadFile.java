package com.example.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UploadFile {
    @Id
    private int seq;
    private String originalFileName;
    private String uuid;
    private String memberId; //등록하는 사람
    private String secretFlag; //비밀글 플래그임 //플래그는 멤버에서 탈퇴시에도 적용.
    private String regDate; //등록일자.타입의 경우 데이트타입으로하면 시간 계산이 편해 로직구현에 좋지만, DB마다 달라 스트링이 선호되는 경우가 있음.
    //업로드자, 업로드일자....
}

