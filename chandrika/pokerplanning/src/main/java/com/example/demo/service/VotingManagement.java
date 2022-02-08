package com.example.demo.service;

import com.example.demo.dto.AddVoteDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.VotesGetByStory;

import java.util.List;

public interface VotingManagement {

	ResponseDto addVoteToUserStory(String idSession, AddVoteDto addVoteDto);

	List<VotesGetByStory> getVotesOfUserStory(String idSession, Integer userStory);

}
