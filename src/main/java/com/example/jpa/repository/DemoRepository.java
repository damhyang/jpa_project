package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.model.Demo;
import java.util.List;


//DemoRepository은 테이블명 +Repository
//인터페이스임.
//jpa는 상속을 받을 때 아래와 같이함.(마이바티스, jdbc와 차이 있음)

@Repository  //스프링에 상속이 되도록 함.
//JpaRepository<Demo, Long>을 제너릭타입이라고 함. 앞은 테이블(엔터티)명와 ,ID의 타입을 적어줌.
public interface DemoRepository extends JpaRepository<Demo, Long> {
    Demo findByUser(String user);
    //Demo findByUser(String user);
    List<Demo> findBySeq(long seq);
}
