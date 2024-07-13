package com.codeventure;

import com.codeventure.entities.Role;
import com.codeventure.entities.User;
import com.codeventure.entities.UserRole;
import com.codeventure.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CodeVentureApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Value("${project.resume}")
	private String ResumePath;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(CodeVentureApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting here...");

//		FOR ADMIN
	//	User user = new User();
	//	user.setId(2);
	//	user.setFirstName("tayaba");
		//user.setLastName("kazmi");
	//user.setUsername("tayaba123");
	//user.setPassword(this.passwordEncoder.encode("tayaba123"));
	//user.setEmail("tayabakazmi123@gmail.com");
	//user.setPhone("03368019186");

	//Role role1 = new Role();
	//role1.setRoleId(22);
	//	role1.setRoleName("ADMIN");

	//UserRole userRole = new UserRole();
	//userRole.setRole(role1);
	//userRole.setUser(user);
	//Set<UserRole> userRolesSet = new HashSet<>();
	//	userRolesSet.add(userRole);

	//	User user1 = this.userService.createUser(user, userRolesSet);
		//System.out.println(user1.getUsername());

//		FOR MENTOR
//		User user = new User();
//		user.setId(2);
//		user.setFirstName("Sarvech");
//		user.setLastName("Rehmani");
//		user.setUsername("sarooreh");
//		user.setPassword(this.passwordEncoder.encode("saroo123"));
//		user.setEmail("sarvechk99@gmail.com");
//		user.setPhone("03133599267");
//
//		Role role1 = new Role();
//		role1.setRoleId(24);
//		role1.setRoleName("MENTOR");
//
//		UserRole userRole = new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//		Set<UserRole> userRolesSet = new HashSet<>();
//		userRolesSet.add(userRole);
//
//		User user1 = this.userService.createUser(user, userRolesSet);
//		System.out.println(user1.getUsername());
	}
}
