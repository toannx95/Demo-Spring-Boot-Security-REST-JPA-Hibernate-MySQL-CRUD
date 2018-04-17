package com.spring.security.config;

import java.util.HashSet;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.spring.security.entity.Role;
import com.spring.security.entity.User;
import com.spring.security.enumeration.RoleEnum;
import com.spring.security.exception.BadRequestException;
import com.spring.security.repository.RoleRepository;
import com.spring.security.repository.UserRepository;
import com.spring.security.util.BCryptPassword;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// init ROLE_ADMIN and ROLE_MEMBER into DB
		if (Objects.isNull(roleRepository.findByName(RoleEnum.ADMIN.getRole()))) {
			roleRepository.save(new Role(RoleEnum.ADMIN.getRole()));
		}

		if (Objects.isNull(roleRepository.findByName(RoleEnum.MEMBER.getRole()))) {
			roleRepository.save(new Role(RoleEnum.MEMBER.getRole()));
		}

		// init two user: admin and member into DB
		if (Objects.isNull(userRepository.findByUserName("admin"))) {
			User user = new User();
			user.setUserName("admin");
			try {
				user.setPassword(BCryptPassword.cryptPasswordEncoder("admin"));
			} catch (BadRequestException e) {
				e.printStackTrace();
			}
			user.setEmail("");
			user.setActive(true);

			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName(RoleEnum.ADMIN.getRole()));
			roles.add(roleRepository.findByName(RoleEnum.MEMBER.getRole()));
			user.setRoles(roles);
			
			userRepository.save(user);
		}

		if (Objects.isNull(userRepository.findByUserName("member"))) {
			User user = new User();
			user.setUserName("member");
			try {
				user.setPassword(BCryptPassword.cryptPasswordEncoder("member"));
			} catch (BadRequestException e) {
				e.printStackTrace();
			}
			user.setEmail("");
			user.setActive(true);

			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName(RoleEnum.MEMBER.getRole()));
			user.setRoles(roles);
			
			userRepository.save(user);
		}
	}

}
