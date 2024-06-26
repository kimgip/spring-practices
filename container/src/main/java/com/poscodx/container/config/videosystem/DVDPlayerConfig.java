package com.poscodx.container.config.videosystem;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.poscodx.container.videosystem.Avengers;
import com.poscodx.container.videosystem.DVDPlayer;
import com.poscodx.container.videosystem.DigitalVideoDisc;
import com.poscodx.container.videosystem.IronMan;

@Configuration
public class DVDPlayerConfig {
	@Bean // 컨테이너가 빈 생성
	public DigitalVideoDisc avangers() {
		return new Avengers();
	}

	@Bean // 컨테이너가 빈 생성
	public DigitalVideoDisc ironMan() {
		return new IronMan();
	}
	
	// 주입(Injection)하기 1
	// Bean 생성 메소드를 직접 호출하는 방법
	// 생성자 주입
	@Bean("dvdPlayer01")
	public DVDPlayer dvdPlayer1() { 
		return new DVDPlayer(avangers());
	}

	// 주입(Injection)하기 2
	// Parameter로 bean을 전달하는 방법
	// 생성자 주입
	@Bean
	public DVDPlayer dvdPlayer2(@Qualifier("avangers") DigitalVideoDisc dvd) {
		return new DVDPlayer(dvd);
	}
	
	// 주입(Injection)하기 3
	// Parameter로 bean을 전달하는 방법
	// setter 주입
	@Bean("dvdPlayer03")
	public DVDPlayer dvdPlayer3(@Qualifier("avangers") DigitalVideoDisc dvd) {
		DVDPlayer dvdPlayer = new DVDPlayer();
		dvdPlayer.setDvd(dvd);
		return dvdPlayer;
	}
}
