package com.liberty.interview.service;

import java.util.List;

import com.liberty.interview.domain.UserStory;
import com.liberty.interview.dto.UserStoryDto;

public interface UserStoryService {

	List<UserStoryDto> findAllStories(String idSession);

	UserStory findUserStoryAsEntity(String idSession, String idUserStory);

	UserStoryDto createStory(String idSession, UserStoryDto userStoryDto);

	UserStoryDto updateStory(String idSession, String idUserStory, UserStoryDto userStoryDto);

	UserStoryDto deleteStory(String idSession, String idUserStory);

	void validateUserStoryExists(String idSession, String idUserStory);

}
