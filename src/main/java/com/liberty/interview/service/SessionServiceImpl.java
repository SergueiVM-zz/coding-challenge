package com.liberty.interview.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liberty.interview.domain.Session;
import com.liberty.interview.dto.SessionDto;
import com.liberty.interview.exception.NotFoundException;
import com.liberty.interview.mapper.SessionMapper;
import com.liberty.interview.repository.SessionRepository;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private SessionMapper sessionMapper;

	@Override
	public SessionDto findSessionById(String idSession) {
		return sessionMapper.modelToDto(findSessionByIdAsEntity(idSession));
	}

	@Override
	public Session findSessionByIdAsEntity(String idSession) {
		Optional<Session> session = sessionRepository.findById(idSession);
		if (!session.isPresent()) {
			throw new NotFoundException(String.format("The session with id '%s' doesn't exist", idSession));
		}
		return session.get();
	}

	@Override
	public List<SessionDto> findAllSessions() {
		List<Session> sessions = sessionRepository.findAll();
		return sessions.stream().map(sessionMapper::modelToDto).collect(Collectors.toList());
	}

	@Override
	public SessionDto createSession(SessionDto sessionDto) {
		Session session = sessionRepository.save(sessionMapper.dtoToModel(sessionDto));
		return sessionMapper.modelToDto(session);
	}

	@Override
	public SessionDto deleteSessionById(String idSession) {
		SessionDto sessionDto = findSessionById(idSession);
		sessionRepository.deleteById(idSession);
		return sessionDto;
	}

	@Override
	public void validateSessionExists(String idSession) {
		if (!sessionRepository.existsById(idSession)) {
			throw new NotFoundException(String.format("The session with id '%s' doesn't exist", idSession));
		}
	}

}
