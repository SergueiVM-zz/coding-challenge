package com.example.demo.service;

import com.example.demo.dto.*;

import java.util.List;

public interface PokerBoardService {
	PokerPlanningSessionDto createPokerSession(PokerSessionRequestDto pokerSessionRequestDto);

	PokerPlanningSessionDto getPokerSession(String idSession);

	MemberDto addMember(MemberDtoRequest memberDtoRequest);

	List<MemberDto> getSessionMembers(String idSession);

	UserStoryDto addUserStory(UserStoryDtoRequest userStoryDtoRequest);

	List<UserStoryDto> getSessionUserStories(String idSession);

	List<PokerPlanningSessionDto> getAllPokerSession();

	ResponseDto deleteSession(String idSession);

	ResponseDto startOrStopVotesOfUserStory(String idSession, Integer userStory, String status);

	ResponseDto deleteUserStory(Integer userStory);

}
