package com.codky.book.springboot2webservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing가 삭제됨
@SpringBootApplication //스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정, 항상 프로젝트의 최상단에 위치
public class Springboot2Webservice2Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot2Webservice2Application.class, args); // 내장 WAS 실행(별도로 외부에 WAS를 두지 않고 애플리케이션 실행시 내부에서 WAS를 실행), 톰캣설치 필요가 없게 되고, Jar파일(Java 패키징 파일)로 실행
    }

}
