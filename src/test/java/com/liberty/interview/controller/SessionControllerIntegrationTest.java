package com.liberty.interview.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.liberty.interview.PokerPlanningApplication;
import com.liberty.interview.dto.SessionDto;

@SpringBootTest(classes = PokerPlanningApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SessionControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private static final String BASE_URL = "http://localhost:%s/sessions";

	@Test
	@DisplayName("Create session")
	@Order(1)
	void createSession() {

		SessionDto sessionDto = new SessionDto();
		sessionDto.setIdSession(UUID.randomUUID().toString());
		sessionDto.setTitle("Poker Planning Session");

		ResponseEntity<SessionDto> createdSessionResponse = getAuthenticatedRestTemplate().postForEntity(getUrl(),
				sessionDto, SessionDto.class);

		assertEquals(HttpStatus.CREATED, createdSessionResponse.getStatusCode());
	}

	@Test
	@DisplayName("Read sessions")
	@Order(2)
	void readSessions() {
		ArrayList<?> readedSessionsResponse = getAuthenticatedRestTemplate().getForObject(getUrl(), ArrayList.class);
		assertTrue(readedSessionsResponse.size() > 0);
	}

	private TestRestTemplate getAuthenticatedRestTemplate() {
		return this.restTemplate.withBasicAuth("user", "liberty");
	}

	private String getUrl() {
		return String.format(BASE_URL, this.port);
	}

}
