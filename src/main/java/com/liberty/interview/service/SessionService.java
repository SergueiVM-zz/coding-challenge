package com.liberty.interview.service;

import java.util.List;

import com.liberty.interview.domain.Session;
import com.liberty.interview.dto.SessionDto;

public interface SessionService {

	SessionDto findSessionById(String idSession);

	Session findSessionByIdAsEntity(String idSession);

	List<SessionDto> findAllSessions();

	SessionDto createSession(SessionDto sessionDto);

	SessionDto deleteSessionById(String idSession);

	void validateSessionExists(String idSession);

}
