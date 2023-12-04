package com.example.jpa.model;

import jakarta.persistence.Entity; //현재 3.대인데 2.대는 jakarta가 아니라 자박스
// import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
// import jakarta.persistence.GenerationType;

@Entity  //Demo라는 테이블을 설정하는 어노테이션
@Data //롬북설정으로 게터세터하지 않음.
public class Demo {
    //테이블내에는 컬럼이 있음. 따라서 데모(테이블)라는 클래스 안에 설정
    @Id //객체가 인식하도록 하는 어노테이션
    // @GeneratedValue(strategy = GenerationType.AUTO)
    //컬럼을 선언함.
    private long seq;  //private라고 선언되어 객체지향에서의 인캡슐화되어버림, 이를 쓸 수 잇게 게터세터를 해줌.
    private String user;


    
    // public long getSeq(){return this.seq;}
    // public String getUser(){return this.user;}
    // public void setSeq(long seq){this.seq=seq;}
    // public void setUser(String user){this.user=user;}  //이걸 케터세터 만드는 것을 롬북(Lombok)dl대신해줌.
}
