package com.vivek.postify;

import com.vivek.postify.Repo.UserRepo;
import com.vivek.postify.modal.GenericResponse;
import com.vivek.postify.modal.User;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;
	@Autowired
	UserRepo userRepo;

	@Before
	public void cleanUp(){
		userRepo.deleteAll();
	}
	public User createUser(){
		User user=new User();
		user.setUsername("vivek");
		user.setPassword("P4hsgasd");
		user.setDisplayname("ballu");
		return user;
	}

	@Test
	public void postUser_whenItOk_UserSavedToDB() {
		User user=createUser();
		testRestTemplate.postForEntity("/users", user, Object.class);
		assertThat(userRepo.count()).isEqualTo(1);
	}

	@Test
	public void postUser_whenItOk_reciveOk() {
		User user=createUser();
		ResponseEntity<Object> responseEntity = testRestTemplate.postForEntity("/users", user, Object.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void postUser_whenItOk_Response() {
		User user=createUser();
		ResponseEntity<GenericResponse> responseEntity =testRestTemplate.postForEntity("/users", user, GenericResponse.class);
		assertThat(responseEntity.getBody().getMessage()).isNotNull();
	}


	@Test
	public void postUser_whenItOk_passwordEncoded() {
		userRepo.deleteAll();
		User user=createUser();
		testRestTemplate.postForEntity("/users", user, Object.class);
		List<User> users=userRepo.findAll();
		assertThat(users.get(0).getPassword()).isNotEqualTo(user.getPassword());
	}




}
