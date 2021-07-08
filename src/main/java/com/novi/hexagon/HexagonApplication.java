package com.novi.hexagon;

import com.novi.hexagon.model.Demo;
import com.novi.hexagon.model.User;
import com.novi.hexagon.property.FileStorageProperties;
import com.novi.hexagon.repository.UserRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(HexagonApplication.class, args);
	}



	@Bean
	InitializingBean sendDatabase() {
		Set<String> h = new HashSet<String>(Arrays.asList("a", "b", "c"));
		return () -> {
			userRepository.save(new User("admin","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8.wtZT3JEMGz8aTyl4Yx2KO"));
			userRepository.save(new User("user","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8.wtZT3JEMGz8aTyl4Yx2KO"));
			userRepository.save(new User("malou_allertz","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8" +
					".wtZT3JEMGz8aTyl4Yx2KO","malou_allertz@hexagon.com","Malou_Allertz.jpg","1995-01-01","Malou",
					"Allertz","","Utrecht","female"));
			userRepository.save(new User("peter_anema","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8" +
					".wtZT3JEMGz8aTyl4Yx2KO","peter_anema@hexagon.com","Peter_Anema.jpg","1970-01-01","Peter",
					"Anema","","Utrecht","male"));
			userRepository.save(new User("pieter_van_dorst","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8" +
					".wtZT3JEMGz8aTyl4Yx2KO","peter_van_dorst@hexagon.com","Pieter_van_Dorst.jpg","1995-01-01","Pieter",
					"van Dorst","","Roosendaal","male"));
			userRepository.save(new User("nova_eeken","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8" +
					".wtZT3JEMGz8aTyl4Yx2KO","nova_eeken@hexagon.com","Nova_Eeken.jpg","1995-01-01","Nova",
					"Eeken","","Utrecht","female"));
			userRepository.save(new User("don_diablo","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8" +
					".wtZT3JEMGz8aTyl4Yx2KO","dondiablo@hexagon.com","DonDiablo.jpg","1980-27-02","Don Pepijn",
					"Schipper","","Coevorden","male"));
			userRepository.save(new User("koen_hopmans","$2a$10$nxtal2oRED1sgaTgzA16duh6usk38H8" +
					".wtZT3JEMGz8aTyl4Yx2KO","koenhopmans@hotmail.com","Koen_Hopmans.PNG","1989-17-11","Koen",
					"Hopmans","","Bergen op Zoom","male"));
			userService.addAuthority("admin", "ROLE_ADMIN");
			userService.addAuthority("peter_anema", "ROLE_ADMIN");
			userService.addAuthority("nova_eeken", "ROLE_ADMIN");
			userService.addAuthority("don_diablo", "ROLE_ADMIN");
		};
	}
}
