package com.example.springconditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;

	private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
			.withExposedPorts(8081);
	private static final GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
			.withExposedPorts(8080);

	@BeforeAll
	public static void setUp() {
		prodApp.start();
		devApp.start();
	}

	@Test
	void contextLoads() {
		ResponseEntity<String> devForEntityResult = restTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(8080) + "/profile", String.class);
		ResponseEntity<String> prodForEntityResult = restTemplate.getForEntity("http://localhost:" + prodApp.getMappedPort(8081) + "/profile", String.class);
		String devStringExpected = "Current profile is dev";
		String prodStringExpected = "Current profile is production";

		Assertions.assertEquals(devStringExpected, devForEntityResult.getBody());
		Assertions.assertEquals(prodStringExpected, prodForEntityResult.getBody());
	}

}
