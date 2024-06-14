package com.poscodx.container.config.soundsystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //Container 설정 자바 클래스이다
@ComponentScan(basePackages={"com.poscodx.container.soundsystem"}) //Auto-Scan
public class CDPlayerConfig { // 명시적 빈 설정
}
