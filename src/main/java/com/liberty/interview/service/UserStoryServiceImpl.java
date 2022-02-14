package com.liberty.interview.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.liberty.interview.domain.UserStory;
import com.liberty.interview.dto.UserStoryDto;
import com.liberty.interview.exception.MismatchException;
import com.liberty.interview.exception.NotFoundException;
import com.liberty.interview.mapper.UserStoryMapper;
import com.liberty.interview.repository.UserStoryRepository;

@Service
public class UserStoryServiceImpl implements UserStoryService {

	@Autowired
	private SessionService sessionService;

	@Autowired
	private UserStoryRepository userStoryRepository;

	@Autowired
	private UserStoryMapper userStoryMapper;

	@Override
	public List<UserStoryDto> findAllStories(String idSession) {
		sessionService.validateSessionExists(idSession);

		return userStoryRepository.findByIdSession(idSession).stream().map(userStoryMapper::modelToDto)
				.collect(Collectors.toList());
	}

	@Override
	public UserStory findUserStoryAsEntity(String idSession, String idUserStory) {
		Optional<UserStory> userStoryOpt = userStoryRepository.findUserStory(idSession, idUserStory);

		if (!userStoryOpt.isPresent()) {
			throw new NotFoundException(
					String.format("The user story with id '%s' belonging to the session with id '%s' doesn't exist",
							idUserStory, idSession));
		}
		return userStoryOpt.get();
	}

	@Override
	public UserStoryDto createStory(String idSession, UserStoryDto userStoryDto) {
		sessionService.validateSessionExists(idSession);

		UserStory userStory = userStoryMapper.dtoToModel(userStoryDto);
		userStory.setSession(sessionService.findSessionByIdAsEntity(idSession));
		return userStoryMapper.modelToDto(userStoryRepository.save(userStory));
	}

	@Override
	public UserStoryDto updateStory(String idSession, String idUserStory, UserStoryDto userStoryDto) {
		validateUserStoryExists(idSession, idUserStory);

		if (!idUserStory.equals(userStoryDto.getIdUserStory())) {
			throw new MismatchException("Ids error mismatch");
		}

		UserStory userStory = userStoryRepository.save(userStoryMapper.dtoToModel(userStoryDto));
		return userStoryMapper.modelToDto(userStory);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserStoryDto deleteStory(String idSession, String idUserStory) {
		validateUserStoryExists(idSession, idUserStory);

		Optional<UserStory> userStoryOpt = userStoryRepository.findUserStory(idSession, idUserStory);

		if (userStoryOpt.isPresent()) {
			UserStory userStory = userStoryOpt.get();
			userStoryRepository.delete(userStory);
			return userStoryMapper.modelToDto(userStory);
		}
		return null;
	}

	@Override
	public void validateUserStoryExists(String idSession, String idUserStory) {
		Optional<UserStory> userStory = userStoryRepository.findUserStory(idSession, idUserStory);

		if (!userStory.isPresent()) {
			throw new NotFoundException(
					String.format("The user story with id '%s' belonging to the session with id '%s' doesn't exist",
							idUserStory, idSession));
		}
	}

}
