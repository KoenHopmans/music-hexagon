package com.novi.hexagon;

import com.novi.hexagon.model.Demo;
import com.novi.hexagon.model.User;
import com.novi.hexagon.property.FileStorageProperties;
import com.novi.hexagon.repository.UserRepository;
import com.novi.hexagon.service.DemoService;
import com.novi.hexagon.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class HexagonApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	DemoService demoService;

	public static void main(String[] args) {
		SpringApplication.run(HexagonApplication.class, args);
	}

//============================================================================================================
//					BEGIN - FUNCTION FOR RESETTING ALL INITIAL USERS
//============================================================================================================
//	@Bean
//	InitializingBean sendDatabase() {
//		Set<String> h = new HashSet<String>(Arrays.asList("a", "b", "c"));
//		return () -> {
//			userRepository.save(new User("admin","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8.wtZT3JEMGz8aTyl4Yx2KO"));
//			userRepository.save(new User("user","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8.wtZT3JEMGz8aTyl4Yx2KO"));
//			userRepository.save(new User("malou_allertz","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8" +
//					".wtZT3JEMGz8aTyl4Yx2KO","malou_allertz@hexagon.com","Malou_Allertz.jpg","1995-01-01","Malou",
//					"Allertz","","Utrecht","female"));
//			userRepository.save(new User("peter_anema","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8" +
//					".wtZT3JEMGz8aTyl4Yx2KO","peter_anema@hexagon.com","Peter_Anema.jpg","1970-01-01","Peter",
//					"Anema","","Utrecht","male"));
//			userRepository.save(new User("pieter_van_dorst","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8" +
//					".wtZT3JEMGz8aTyl4Yx2KO","peter_van_dorst@hexagon.com","Pieter_van_Dorst.jpg","1995-01-01","Pieter",
//					"van Dorst","","Roosendaal","male"));
//			userRepository.save(new User("nova_eeken","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8" +
//					".wtZT3JEMGz8aTyl4Yx2KO","nova_eeken@hexagon.com","Nova_Eeken.jpg","1995-01-01","Nova",
//					"Eeken","","Utrecht","female"));
//			userRepository.save(new User("don_diablo","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8" +
//					".wtZT3JEMGz8aTyl4Yx2KO","dondiablo@hexagon.com","DonDiablo.jpg","1980-27-02","Don Pepijn",
//					"Schipper","","Coevorden","male"));
//			userRepository.save(new User("koen_hopmans","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8" +
//					".wtZT3JEMGz8aTyl4Yx2KO","koenhopmans@hotmail.com","Koen_Hopmans.PNG","1989-17-11","Koen",
//					"Hopmans","","Bergen op Zoom","male"));
//			userService.addAuthority("admin", "ROLE_ADMIN");
//			userService.addAuthority("peter_anema", "ROLE_ADMIN");
//			userService.addAuthority("nova_eeken", "ROLE_ADMIN");
//			userService.addAuthority("don_diablo", "ROLE_ADMIN");
//			demoService.addDemo(new Demo("don_diablo","La-La-Land.mp3","la-la-land.jpg","Don Diabo","La La Land"));
//			demoService.addDemo(new Demo("peter_anema","bach.mp3","bach.jpg","DJ Peter","Das Air"));
//			demoService.addDemo(new Demo("malou_allertz","How-i-like-it.mp3","island.jpg","DJ Allertzo","How I like " +
//					"it"));
//			demoService.addDemo(new Demo("malou_allertz","bull.mp3","bull.jpg","DJ Allertzo","Black Bull"));
//			demoService.addDemo(new Demo("pieter_van_dorst","future.mp3","future.jpg","DJ Pieter","Future"));
//			demoService.addDemoComment("How-i-like-it.mp3","Dit is mijn eerste nummer, Wat vinden jullie ervan?",
//					"2021-07-10","malou_allertz");
//			demoService.addDemoFeedback("How-i-like-it.mp3","Mooi gemaakt je hebt echt talent!",
//					"2021-07-11","don_diablo");
//			demoService.addDemoComment("bach.mp3","Prachtig Peter, dit is ook mijn favoriete muziek!",
//					"2021-07-09","nova_eeken");
//			demoService.addDemoFeedback("future.mp3","Gaaf, er zit een goede opbouw in en de beat is echt fantastisch.",
//					"2021-06-28","don_diablo");
//		};
//	}
//============================================================================================================
//					END - FUNCTION FOR RESETTING ALL INITIAL USERS
//============================================================================================================
}
