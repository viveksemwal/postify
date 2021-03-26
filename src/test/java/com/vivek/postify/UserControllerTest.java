package com.vivek.postify;

import com.vivek.postify.modal.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	public void postuser_whenItOk_reciveOk() {
		User user=new User();
		user.setUsername("vivek");
		user.setPassword("P4hsgasd");
		user.setDisplayname("ballu");

		ResponseEntity<Object> responseEntity = testRestTemplate.postForEntity("/users", user, Object.class);


	}

}
